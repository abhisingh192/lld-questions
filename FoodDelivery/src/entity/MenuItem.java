package entity;

public class MenuItem {

    private final String id, name;
    private final double price;

    private final boolean isAvailable;

    public MenuItem(String id, String name, double price, boolean available) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isAvailable = available;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", isAvailable=" + isAvailable +
                '}';
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }






}
