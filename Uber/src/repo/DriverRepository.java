package repo;

import entity.Driver;
import entity.Location;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class DriverRepository {

    private final ConcurrentHashMap<String, Driver> drivers = new ConcurrentHashMap<>();

    public void addDriver(Driver driver) {
        drivers.put(driver.getId(), driver);
    }

    public Driver getDriverById(String driverId) {
        return drivers.get(driverId);
    }

    public Collection<Driver> getAllDrivers() {
        return drivers.values();
    }

    public void updateLocation(String driverId, Location loc) {
        Driver d = drivers.get(driverId);
        if (d != null)
            d.setLocation(loc);
    }

    public void setAvailability(String driverId, boolean avail) {
        Driver d = drivers.get(driverId);
        if (d != null)
            d.setAvailable(avail);
    }
}
