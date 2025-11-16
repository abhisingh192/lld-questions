package entity;

public class Product {

    private final String id;

    private final String name;

    private final String categoryId;

    private final double price;

    private final String description;


    public Product(String id, String name, String categoryId, double price, String description) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.price = price;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("%s (₹%.2f) - %s", name, price, description);
    }


}
