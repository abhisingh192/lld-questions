package service;

import entity.InventoryItem;
import repo.InventoryRepository;

public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    /**
     * Reserve stock for a product atomically.
     * Returns true if reservation succeed.
     */
    public boolean reserve(String productId, int qty) {
        InventoryItem it = inventoryRepository.getByProductId(productId);
        if (it == null) return false;
        synchronized (it.lock) {
            if (it.getStockQuantity() < qty) return false;
            it.setStockQuantity( it.getStockQuantity() - qty);
            return true;
        }
    }

    /**
     * Release previously reserved stock.
     */
    public void release(String productId, int qty) {
        InventoryItem it = inventoryRepository.getByProductId(productId);
        if (it == null) return;
        synchronized (it.lock) {
            it.setStockQuantity( it.getStockQuantity() + qty);
        }
    }

    public int getStock(String productId) {
        InventoryItem it = inventoryRepository.getByProductId(productId);
        return it == null ? 0 : it.getStockQuantity();
    }

}
