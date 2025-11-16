package repo;

import entity.TrafficLight;

import java.util.ArrayList;
import java.util.List;

public class TrafficLightRepository {

    private List<TrafficLight> trafficLights = new ArrayList<>();

    public void addTrafficLight(TrafficLight light) {
        trafficLights.add(light);
    }

    public List<TrafficLight> getAllTrafficLights() {
        return trafficLights;
    }



}
