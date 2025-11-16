import entity.*;
import repo.DriverRepository;
import repo.RideRepository;
import service.MatchingService;
import service.PaymentService;
import service.PricingService;
import service.RideService;

public class Main {
    public static void main(String[] args) {

        // Data layer
        DriverRepository driverRepo = new DriverRepository();
        RideRepository rideRepo = new RideRepository();

        // Add drivers
        driverRepo.addDriver(new Driver("D1","Ravi", RideType.REGULAR,new Location(12.96,77.64)));
        driverRepo.addDriver(new Driver("D2","Asha",RideType.PREMIUM,new Location(12.97,77.65)));

        // Initialize services
        MatchingService matching = new MatchingService(driverRepo);
        PricingService pricing = new PricingService();
        PaymentService payment = new PaymentService();
        RideService rideService = new RideService(rideRepo, driverRepo, matching, pricing, payment);

        // Passenger
        Passenger p = new Passenger("P1","Neha", 2000.0);

        // Request a ride
        Ride ride = rideService.requestRide(
                p,
                new Location(12.961,77.640),
                new Location(12.990,77.700),
                RideType.REGULAR
        );

        if (ride.getDriver() != null) {
            rideService.startRide(ride.getId());

            // Simulate travel delay
            try { Thread.sleep(2000); } catch(Exception ignored){}

            rideService.completeRide(ride.getId());
        }
    }

}