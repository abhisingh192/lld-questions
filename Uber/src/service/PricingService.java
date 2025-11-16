package service;

import entity.RideType;

public class PricingService {

    public double calculateFare(double distanceKm, RideType type) {
        if (type == RideType.REGULAR) {
            return 30 + distanceKm * 12;
        }
        return 60 + distanceKm * 20;
    }

}
