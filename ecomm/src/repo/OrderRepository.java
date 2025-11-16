package repo;

import entity.Order;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class OrderRepository {

    private final Map<String, Order> orderMap = new HashMap<>();

    public void add(Order order) {
        orderMap.put(order.getId(), order);
    }

    public Order getById(String orderId) {
        return orderMap.get(orderId);
    }

    public Collection<Order> all() {
        return orderMap.values();
    }


}
