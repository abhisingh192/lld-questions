package entity;

public class InventoryItem {

    private final String productId;
    private int stockQuantity;
    public final Object lock = new Object();



    public InventoryItem(String productId, int stockQuantity) {
        this.productId = productId;
        this.stockQuantity = stockQuantity;
    }

    public String getProductId() {
        return productId;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
