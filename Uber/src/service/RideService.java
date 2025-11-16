package service;

import entity.*;
import repo.DriverRepository;
import repo.RideRepository;

import java.util.UUID;

public class RideService {

    private final RideRepository rideRepo;
    private final DriverRepository driverRepo;
    private final MatchingService matcher;
    private final PricingService pricing;
    private final PaymentService payments;

    public RideService(RideRepository rr, DriverRepository dr, MatchingService m,
                       PricingService p, PaymentService pay) {
        this.rideRepo = rr;
        this.driverRepo = dr;
        this.matcher = m;
        this.pricing = p;
        this.payments = pay;
    }

    public Ride requestRide(Passenger p, Location pickup, Location drop, RideType type) {

        Ride ride = new Ride(UUID.randomUUID().toString(), p, pickup, drop, type);
        rideRepo.addRide(ride);

        Driver d = matcher.findNearestDriver(pickup, type, 10.0);

        if (d == null) {
            System.out.println("No drivers available for ride " + ride.getId());
//            ride.setStatus(RideStatus.CANCELLED);
            return ride;
        }

        d.lock.lock();
        try {
            if (!d.isAvailable()) {
                System.out.println("Driver " + d.getId() + " became unavailable.");
                return ride;
            }

            d.setAvailable(false);
            ride.setDriver(d);
            ride.setStatus(RideStatus.MATCHED);
            System.out.println("Ride " + ride.getId() + " matched with driver " + d.getName());

        } finally {
            d.lock.unlock();
        }
        return ride;
    }

    public void startRide(String rideId) {
        Ride ride = rideRepo.getRideById(rideId);
        ride.getLock().lock();

        try {
            ride.setStartedAt(java.time.Instant.now());
            ride.setStatus(RideStatus.STARTED);
            System.out.println("Ride " + rideId + " started.");
        } finally {
            ride.getLock().unlock();
        }
    }

    public void completeRide(String rideId) {
        Ride ride = rideRepo.getRideById(rideId);
        ride.getLock().lock();

        try {
            ride.setCompletedAt(java.time.Instant.now());
            ride.setDistanceKm(ride.getPickup().distanceKmTo(ride.getDropoff()));
            ride.setFare(pricing.calculateFare(ride.getDistanceKm(), ride.getType()));

            boolean isCharged = payments.chargePassenger(ride.getPassenger(), ride.getFare());
            if (!isCharged) {
                System.out.println("Payment failed for ride " + rideId);
                return;
            }
            payments.payDriver(ride.getDriver(), ride.getFare()*0.8);
            ride.getDriver().setAvailable(true);

            ride.setStatus(RideStatus.COMPLETED);
            System.out.println("Ride " + rideId + " completed. Fare: " + ride.getFare());
        } finally {
            ride.getLock().unlock();
        }
    }
}
