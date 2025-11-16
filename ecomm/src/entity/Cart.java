package entity;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private final String userId;
    private final Map<String, CartItem> items = new HashMap<>();

    public Cart(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public Map<String, CartItem> getItems() {
        return items;
    }

    public void add(String productId, int qty) {
        items.putIfAbsent(productId, new CartItem(productId, 0));
        items.get(productId).setQuantity( items.get(productId).getQuantity() + qty);
    }

    public void remove(String productId) {
        items.remove(productId);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}


