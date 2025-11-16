package service;

import entity.Driver;
import entity.Location;
import entity.RideType;
import repo.DriverRepository;

public class MatchingService {

    private final DriverRepository driverRepository;

    public MatchingService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public Driver findNearestDriver(Location pickup, RideType rideType, double radiusKm) {
        Driver best = null;
        double bestDist = Double.MAX_VALUE;

        for (Driver d : driverRepository.getAllDrivers()) {
            if (!d.isAvailable() || d.getType() != rideType) continue;
            double dist = d.getLocation().distanceKmTo(pickup);
            if (dist < bestDist && dist <= radiusKm) {
                best = d; bestDist = dist;
            }
        }
        return best;
    }
}
