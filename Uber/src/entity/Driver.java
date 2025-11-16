package entity;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Driver {
    private final String id;
    private final String name;
    private final RideType type;
    private volatile boolean available = true;
    private volatile Location location;
    private double earnings = 0.0;

    public final Lock lock = new ReentrantLock();

    public Driver(String id, String name, RideType type, Location location) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public RideType getType() {
        return type;
    }

    public boolean isAvailable() {
        return available;
    }



    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public double getEarnings() {
        return earnings;
    }

    public void addEarnings(double amount) {
        this.earnings += amount;
    }



}
