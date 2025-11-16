import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<ParkingSpot> level1Spots = Arrays.asList(
                new ParkingSpot("L1S1", VehicleSize.MEDIUM),
                new ParkingSpot("L1S2", VehicleSize.LARGE),
                new ParkingSpot("L1S3", VehicleSize.SMALL)
        );

        Level level1 = new Level(1, level1Spots);
        ParkingLot lot = new ParkingLot(Arrays.asList(level1));

        Vehicle car = new Car("KA01AB1234");
        ParkingTicket ticket = lot.parkVehicle(car);

        if (ticket != null) {
            System.out.println("Car parked at spot: " + ticket.getSpot().getId());
        }
    }
}