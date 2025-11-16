package entity;

public class OrderItem {
    private final String menuId;
    private final String name;
    private final int quantity;

    private final double unitPrice;

    public OrderItem(String menuId, String name, int quantity, double unitPrice) {
        this.menuId = menuId;
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public double lineTotal(){ return unitPrice * quantity; }

    public String getMenuId() {
        return menuId;
    }

    public String getName() {
        return name;
    }


    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }


}
