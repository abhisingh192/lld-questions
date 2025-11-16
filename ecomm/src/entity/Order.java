package entity;

import java.time.Instant;
import java.util.List;

public class Order {

    private final String id;
    private final String userId;
    private final List<OrderItem> items;
    private final double total;
    private String status; // PLACED / PAID / SHIPPED / DELIVERED / CANCELLED
    private final Instant createdAt;

    public Order(String id, String userId, List<OrderItem> items, double total) {
        this.id = id;
        this.userId = userId;
        this.items = items;
        this.total = total;
        this.status = "PLACED";
        this.createdAt = Instant.now();
    }


    @Override
    public String toString() {
        return String.format("Order %s | user=%s | items=%d | total=₹%.2f | status=%s",
                id, userId, items.size(), total, status);
    }

    public String getId() {
        return id;
    }

    public void setStatus(String status) {
        this.status = status;
    }



}
