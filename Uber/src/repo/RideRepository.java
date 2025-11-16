package repo;

import entity.Ride;

import java.util.concurrent.ConcurrentHashMap;

public class RideRepository {

    private final ConcurrentHashMap<String, Ride> rides = new ConcurrentHashMap<>();

    public void addRide(Ride ride) {
        rides.put(ride.getId(), ride);
    }

    public Ride getRideById(String rideId) {
        return rides.get(rideId);
    }



}
