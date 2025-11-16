package service;

import entity.Road;

public class AdaptiveTimingService {

    private static final int MIN_GREEN = 5;
    private static final int MAX_GREEN = 20;
    private static final int MAX_DENSITY = 100; // vehicles/min


    // Calculates green time based on road congestion (simple proportional model)
    public int calculateGreenDuration(Road road) {
        int density = road.getTrafficDensity();
        int duration = MIN_GREEN + (density * (MAX_GREEN - MIN_GREEN)) / MAX_DENSITY;
        return Math.min(duration, MAX_GREEN);
    }
}
