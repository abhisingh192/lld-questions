package service;

import entity.Road;
import entity.Signal;
import entity.SignalType;
import entity.TrafficLight;

import java.util.List;

public class EmergencyHandlerService {

    public void handleEmergency(Road emergencyRoad, List<TrafficLight> trafficLights) {
        System.out.println("🚨 Emergency detected on " + emergencyRoad.getName());
        for (TrafficLight light : trafficLights) {
            if (light.getRoad().equals(emergencyRoad)) {
                light.changeSignal(new Signal(SignalType.GREEN, 15));
            } else {
                light.changeSignal(new Signal(SignalType.RED, 15));
            }
        }
    }
}
