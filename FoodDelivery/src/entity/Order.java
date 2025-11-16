package entity;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Order {

    private final String id;
    private final Customer customer;
    private final Restaurant restaurant;
    public final List<OrderItem> items;
    public final PaymentMethod paymentMethod;

    public volatile OrderStatus orderStatus = OrderStatus.PLACED;
    public volatile PaymentStatus paymentStatus = PaymentStatus.INITIATED;
    public volatile DeliveryAgent agent;

    public final Instant createdAt = Instant.now();
    public volatile Instant updatedAt = createdAt;
    public volatile double total;

    public final Lock lock = new ReentrantLock();


    public Order(String id, Customer c, Restaurant r, List<OrderItem> items, PaymentMethod pm){
        this.id=id; this.customer=c; this.restaurant=r; this.items=items; this.paymentMethod=pm;
    }

    // Getters and setters for orderStatus and paymentStatus
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }


    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }


}
