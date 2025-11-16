package repo;

import entity.DeliveryAgent;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DeliveryAgentRepo {

    private final Map<String, DeliveryAgent> deliveryAgents = new ConcurrentHashMap<>();

    public void save(DeliveryAgent deliveryAgent) {
        deliveryAgents.put(deliveryAgent.getId(), deliveryAgent);
    }

    public DeliveryAgent findById(String id) {
        return deliveryAgents.get(id);
    }

    public Collection<DeliveryAgent> findAll() {
        return deliveryAgents.values();
    }
}
