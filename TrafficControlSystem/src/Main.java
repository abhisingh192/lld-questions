import entity.Road;
import entity.Signal;
import entity.SignalType;
import entity.TrafficLight;
import repo.TrafficLightRepository;
import service.AdaptiveTimingService;
import service.EmergencyHandlerService;
import service.TrafficControllerService;

public class Main {
    public static void main(String[] args)  {
        // Create roads with varying traffic density
        Road roadA = new Road("Road A", 30);
        Road roadB = new Road("Road B", 80);

        // Create initial lights
        TrafficLight lightA = new TrafficLight(roadA, new Signal(SignalType.RED, 10));
        TrafficLight lightB = new TrafficLight(roadB, new Signal(SignalType.GREEN, 10));

        // Store in repository
        TrafficLightRepository repo = new TrafficLightRepository();
        repo.addTrafficLight(lightA);
        repo.addTrafficLight(lightB);

        // Start controller with adaptive timing
        AdaptiveTimingService timingService = new AdaptiveTimingService();
        TrafficControllerService controller = new TrafficControllerService(repo.getAllTrafficLights(), timingService);
        controller.startControl();

        // Simulate emergency after some time
        try {
            Thread.sleep(15000);
            EmergencyHandlerService emergencyService = new EmergencyHandlerService();
            emergencyService.handleEmergency(roadA, repo.getAllTrafficLights());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}