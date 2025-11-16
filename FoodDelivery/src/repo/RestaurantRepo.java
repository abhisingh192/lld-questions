package repo;

import entity.Restaurant;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class RestaurantRepo {

    private final Map<String, Restaurant> restaurants = new ConcurrentHashMap<>();

    public void save(Restaurant restaurant) {
        restaurants.put(restaurant.getId(), restaurant);
    }

    public Restaurant findById(String id) {
        return restaurants.get(id);
    }

    public Collection<Restaurant> findAll() {
        return restaurants.values();
    }



}
