package entity;

import java.time.Instant;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ride {

    private final String id;
    private final Passenger passenger;

    private Driver driver;

    private Location pickup;
    private Location dropoff;

    private final RideType type;

    private double distanceKm;
    private double fare;

    private Instant startedAt;
    private Instant completedAt;

    private RideStatus status = RideStatus.REQUESTED;

    private final Lock lock = new ReentrantLock();


    public Ride(String id, Passenger passenger, Location pickup, Location dropoff, RideType type) {
        this.id = id;
        this.passenger = passenger;
        this.pickup = pickup;
        this.dropoff = dropoff;
        this.type = type;
    }


    public String getId() {
        return id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Location getPickup() {
        return pickup;
    }

    public Location getDropoff() {
        return dropoff;
    }

    public RideType getType() {
        return type;
    }

    public double getDistanceKm() {
        return distanceKm;
    }




    public void setDistanceKm(double distanceKm) {
        this.distanceKm = distanceKm;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public Instant getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Instant startedAt) {
        this.startedAt = startedAt;
    }

    public Instant getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Instant completedAt) {
        this.completedAt = completedAt;
    }

    public RideStatus getStatus() {
        return status;
    }

    public void setStatus(RideStatus status) {
        this.status = status;
    }

    public Lock getLock() {
        return lock;
    }


}
