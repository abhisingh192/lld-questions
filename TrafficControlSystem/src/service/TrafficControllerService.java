package service;

import entity.Signal;
import entity.SignalType;
import entity.TrafficLight;

import java.util.ArrayList;
import java.util.List;

public class TrafficControllerService {

    private List<TrafficLight> trafficLights;
    private AdaptiveTimingService adaptiveTimingService;

    private volatile boolean running = true;

    public TrafficControllerService(List<TrafficLight> trafficLights, AdaptiveTimingService adaptiveTimingService) {
        this.trafficLights = trafficLights;
        this.adaptiveTimingService = adaptiveTimingService;
    }

    // Simulates adaptive control loop
    public void startControl() {
        new Thread(() -> {
            while (running) {
                for (TrafficLight light : trafficLights) {
                    try {
                        int greenDuration = adaptiveTimingService.calculateGreenDuration(light.getRoad());
                        light.changeSignal(new Signal(SignalType.GREEN, greenDuration));
                        Thread.sleep(greenDuration * 1000L);

                        light.changeSignal(new Signal(SignalType.YELLOW, 3));
                        Thread.sleep(3000);

                        light.changeSignal(new Signal(SignalType.RED, 5));
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }).start();
    }

    public void stopControl() {
        running = false;
    }
}
