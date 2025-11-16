package entity;

public class Customer {

    private final String id, name;
    private final Location location;
    private double wallet; // for demo

    public Customer(String id, String name, Location location, double wallet) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.wallet = wallet;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public double getWallet() {
        return wallet;
    }


}
