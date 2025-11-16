import java.util.List;

public class ParkingLot {

    private List<Level> levels;

    public ParkingLot(List<Level> levels) {
        this.levels = levels;
    }

    public ParkingTicket parkVehicle(Vehicle vehicle) {
        for (Level level : levels) {
            ParkingSpot spot = level.parkVehicle(vehicle);
            if (spot != null) {
                return new ParkingTicket(vehicle, spot);
            }
        }
        System.out.println("No spots available for " + vehicle.getSize());
        return null; // Parking lot full
    }

    public void unparkVehicle(ParkingTicket ticket) {
        if (ticket != null) {
            ParkingSpot spot = ticket.getSpot();
            for (Level level : levels) {
                level.releaseSpot(spot);

            }
        }
    }
}
