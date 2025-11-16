public class ParkingSpot {

    private String id;

    private VehicleSize spotType;

    private Vehicle currentVehicle;


    public ParkingSpot(String id, VehicleSize spotType) {
        this.id = id;
        this.spotType = spotType;
        this.currentVehicle = null; // Spot is initially empty
    }

    public synchronized boolean assignVehicle(Vehicle v) {
        if (currentVehicle == null && v.getSize() == spotType) {
            currentVehicle = v;
            return true;
        }
        return false;
    }

    public synchronized void removeVehicle() { currentVehicle = null; }

    public boolean isFree() { return currentVehicle == null; }
    public VehicleSize getSpotType() { return spotType; }
    public String getId() { return id; }
}
