package service;

import entity.*;
import repo.OrderRepository;
import repo.ProductRepository;
import service.strategy.PaymentFactory;
import service.strategy.PaymentMethod;
import service.strategy.PaymentStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * Checkout flow:
 * 1) Validate cart and compute total
 * 2) Reserve inventory per item (per-product lock inside InventoryService)
 * 3) Charge payment via PaymentStrategy
 * 4) Persist order; release inventory on failure
 *
 * For demo simplicity an orderLock is used to avoid complex interleavings.
 * Production should use DB transactions / distributed locks / message queues.
 */
public class OrderService {
    private final OrderRepository orderRepo;
    private final ProductRepository productRepo;
    private final InventoryService inventory;
    private final ReentrantLock orderLock = new ReentrantLock();

    public OrderService(OrderRepository orderRepo, ProductRepository productRepo, InventoryService inventory) {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
        this.inventory = inventory;
    }


    /**
     * Checkout: attempt to place order and pay.
     * Throws RuntimeException on failure.
     */
    public Order checkout(String userId, Cart cart, PaymentMethod pm, User user) {

        if (cart == null || cart.getItems().isEmpty())
            throw new RuntimeException("cart empty");

        // 1) Build order items and compute total
        List<OrderItem> items = cart.getItems().values().stream()
                .map(ci -> {
                    Product p = productRepo.getById(ci.getProductId());
                    if (p == null) throw new RuntimeException("product not found: " + ci.getProductId());
                    return new OrderItem(ci.getProductId(), ci.getQuantity(), p.getPrice());
                })
                .toList();

        double total = items.stream().mapToDouble(OrderItem::lineTotal).sum();
        orderLock.lock();

        try {
            // 2) Reserve inventory for all items; if any fail, rollback
            List<String> reserved = new ArrayList<>();
            for (OrderItem oi : items) {
                boolean ok = inventory.reserve(oi.getProductId(), oi.getQty());
                if (!ok) {
                    // rollback
                    for (String pid : reserved) {
                        OrderItem matched = items.stream().filter(x -> x.getProductId().equals(pid)).findFirst().orElse(null);
                        if (matched != null) inventory.release(pid, matched.getQty());
                    }
                    throw new RuntimeException("out of stock: " + oi.getProductId());
                }
                reserved.add(oi.getProductId());
            }

            // 3) Payment
            PaymentStrategy pay = PaymentFactory.getPaymentStrategy(pm);
            boolean paid = pay.pay(user, total);
            if (!paid) {
                // payment failed -> release reserved inventory
                for (OrderItem oi : items) inventory.release(oi.getProductId(), oi.getQty());
                throw new RuntimeException("payment failed");
            }

            // 4) Persist order and clear cart
            Order order = new Order(UUID.randomUUID().toString(), userId, items, total);
            order.setStatus("PAID");
            orderRepo.add(order);
            cart.getItems().clear();

            return order;

        } finally {
            orderLock.unlock();
        }
    }
}
