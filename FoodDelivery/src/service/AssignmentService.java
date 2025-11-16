package service;

import entity.DeliveryAgent;
import entity.Location;
import repo.DeliveryAgentRepo;

public class AssignmentService {

    private final DeliveryAgentRepo repo;

    public AssignmentService(DeliveryAgentRepo repo){
        this.repo=repo;
    }

    public DeliveryAgent findNearestAvailable(Location from, double radiusKm){
        DeliveryAgent best=null;
        double bestDist=Double.MAX_VALUE;
        for(DeliveryAgent a : repo.findAll()){
            if(!a.isAvailable())
                continue;
            double dist = a.getCurrentLocation().distanceKmTo(from);
            if(dist < bestDist && dist <= radiusKm){ best=a; bestDist=dist; }
        }
        return best;
    }

}
