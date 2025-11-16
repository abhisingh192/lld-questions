package entity;

public class OrderItem {

    private final String productId;
    private final int qty;
    private final double unitPrice;

    public OrderItem(String productId, int qty, double unitPrice) {
        this.productId = productId;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }



    public double lineTotal() {
        return unitPrice * qty;
    }

    public String getProductId() {
        return productId;
    }

    public int getQty() {
        return qty;
    }


}
