package service;

import entity.*;
import notif.EventType;
import notif.Notification;
import notif.NotificationBus;
import repo.OrderRepo;
import repo.RestaurantRepo;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static java.lang.Thread.sleep;

public class OrderService {

    private final OrderRepo orders;
    private final RestaurantRepo restaurants;

    private final AssignmentService assignmentService;

    private final NotificationBus notificationBus;

    public OrderService(OrderRepo orders, RestaurantRepo restaurants, AssignmentService assignmentService, NotificationBus notificationBus) {
        this.orders = orders;
        this.restaurants = restaurants;
        this.assignmentService = assignmentService;
        this.notificationBus = notificationBus;
    }

    public Order placeOrder(Customer c, String restaurantId , List<OrderItem> items, PaymentMethod pm) {
        Restaurant r = restaurants.findById(restaurantId);

        if(r==null || !r.isOpen())
            throw new IllegalStateException("Restaurant unavailable");

        double total = items.stream().mapToDouble(OrderItem::lineTotal).sum();

        Order order = new Order(UUID.randomUUID().toString(), c,r,items,pm);
        order.setTotal(total);

        orders.save(order);

        notificationBus.publish(new Notification(EventType.ORDER_PLACED, "Order placed by "+c.getName(), order.getId()));
        return order;
    }

    public boolean confirmAndAssign(String orderId) {

        Order order = orders.findById(orderId);

        if (order==null)
            return false;

        order.lock.lock();
        try {
            if (order.getOrderStatus() != OrderStatus.PLACED) {
                return false;
            }

            order.setOrderStatus(OrderStatus.CONFIRMED);
            notificationBus.publish(new Notification(EventType.ORDER_CONFIRMED, "Order confirmed by "+order.getRestaurant().getName(), order.getId()));
        } finally {
            order.lock.unlock();
        }

        // Assign agent (with per-agent locking to avoid double-assign)
        DeliveryAgent candidate = assignmentService.findNearestAvailable(order.getRestaurant().getLocation(), 15.0);

        if(candidate==null){
            cancel(orderId, "No agents available");
            return false;
        }

        candidate.getLock().lock();
        try {
            if (!candidate.isAvailable()) {
                cancel(orderId, "No agents available");
                return false;
            }

            candidate.setAvailable(false);
            order.lock.lock();
            try {
                order.agent = candidate;
                order.setOrderStatus(OrderStatus.PREPARING);
                notificationBus.publish(new Notification(EventType.ORDER_PREPARING, "Restaurant started preparing", o.id));
            } finally {
                order.lock.unlock();
            }

        } finally {
            candidate.getLock().unlock();
        }
        // Simulate async progress
        CompletableFuture.runAsync(() -> progressToOutForDelivery(order.getId()));
        return true;

    }

    private void progressToOutForDelivery(String orderId) throws InterruptedException {
        sleep(1000);
        Order o = orders.findById(orderId);
        if(o==null) return;
        o.lock.lock();
        try{
            if(o.getOrderStatus()!=OrderStatus.PREPARING) return;
            o.setOrderStatus(OrderStatus.OUT_FOR_DELIVERY);
            notificationBus.publish(new Notification(EventType.ORDER_OUT_FOR_DELIVERY, "Agent picked up order", o.getId()));
        } finally { o.lock.unlock(); }
    }

    public void deliver(String orderId){
        Order o = orders.findById(orderId);
        if(o==null) return;
        o.lock.lock();
        try{
            if(o.getOrderStatus()!=OrderStatus.OUT_FOR_DELIVERY) return;
            o.setOrderStatus(OrderStatus.DELIVERED);
            if(o.agent!=null){
                o.agent.getLock().lock();
                try{
                    o.agent.setAvailable(true);
                    o.agent.setCurrentLocation(o.getCustomer().getLocation());
                }
                finally { o.agent.getLock().unlock(); }
            }
            notificationBus.publish(new Notification(EventType.ORDER_DELIVERED, "Delivered to "+o.getCustomer().getName(), o.getId()));
        } finally { o.lock.unlock(); }
    }

    public void cancel(String orderId, String reason){
        Order o = orders.findById(orderId);
        if(o==null) return;
        o.lock.lock();
        try{
            o.setOrderStatus(OrderStatus.CANCELLED);
            if(o.agent!=null){
                o.agent.getLock().lock();
                try{
                    o.agent.setAvailable(true);
                } finally { o.agent.getLock().unlock(); }
            }
            notificationBus.publish(new Notification(EventType.ORDER_CANCELLED, "Cancelled: "+reason, o.getId()));
        } finally { o.lock.unlock(); }
    }

}
