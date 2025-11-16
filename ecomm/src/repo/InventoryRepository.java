package repo;

import entity.InventoryItem;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InventoryRepository {

    private final Map<String, InventoryItem> store = new HashMap<>();

    public void add(InventoryItem item) {
        store.put(item.getProductId(), item);
    }

    public InventoryItem getByProductId(String productId) {
        return store.get(productId);
    }

    public Collection<InventoryItem> getAll() {
        return store.values();
    }





}
