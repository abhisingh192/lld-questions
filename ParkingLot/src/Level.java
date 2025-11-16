import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

public class Level {

    private int levelNumber;
    private List<ParkingSpot> parkingSpots;

    private Map<VehicleSize, Queue<ParkingSpot>> freeSpotsMap = new ConcurrentHashMap<>();

    public Level(int levelNumber, List<ParkingSpot> parkingSpots) {
        this.levelNumber = levelNumber;
        this.parkingSpots = parkingSpots;

        // Initialize the free spots map
        for (ParkingSpot spot : parkingSpots) {
            freeSpotsMap.putIfAbsent(spot.getSpotType(), new java.util.concurrent.ConcurrentLinkedQueue<>());
            freeSpotsMap.get(spot.getSpotType()).add(spot);
        }
    }
    public ParkingSpot parkVehicle(Vehicle vehicle) {
        Queue<ParkingSpot> queue = freeSpotsMap.get(vehicle.getSize());
        ParkingSpot spot;
        synchronized (queue) {
            spot = queue.poll();
            if (spot != null && spot.assignVehicle(vehicle)) {
                return spot;
            }
        }
        return null;
    }

    public void releaseSpot(ParkingSpot spot) {
        spot.removeVehicle();
        freeSpotsMap.get(spot.getSpotType()).add(spot);
    }



}
