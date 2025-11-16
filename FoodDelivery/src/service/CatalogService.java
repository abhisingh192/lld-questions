package service;

import entity.Location;
import entity.MenuItem;
import entity.Restaurant;
import repo.RestaurantRepo;

import java.util.List;
import java.util.stream.Collectors;

public class CatalogService {

    private final RestaurantRepo repo;
    public CatalogService(RestaurantRepo repo){ this.repo=repo; }


    public List<Restaurant> listRestaurantsNear(Location loc, double radiusKm){
        return repo.findAll().stream()
                .filter(r -> r.isOpen() && r.getLocation().distanceKmTo(loc) <= radiusKm)
                .collect(Collectors.toList());
    }

    public List<MenuItem> getMenu(String restaurantId){
        Restaurant r = repo.findById(restaurantId);
        if(r==null) return List.of();
        return r.getMenu();
    }


}
