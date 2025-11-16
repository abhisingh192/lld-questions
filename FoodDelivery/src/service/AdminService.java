package service;

import entity.MenuItem;
import entity.Restaurant;
import repo.RestaurantRepo;

public class AdminService {

    private final RestaurantRepo repo;

    public AdminService(RestaurantRepo repo){
        this.repo=repo;
    }

    public void addRestaurant(Restaurant restaurant){
        repo.save(restaurant);
    }

    public void addMenuItem(String restaurantId, MenuItem item){
        Restaurant r = repo.findById(restaurantId);
        if(r!=null){
            r.addItem(item);
        }
    }

    public void removeMenuItem(String restaurantId, String itemId){
        Restaurant r = repo.findById(restaurantId);
        if(r!=null){
            r.removeItem(itemId);
        }
    }

    public void setOpenStatus(String restaurantId, boolean isOpen){
        Restaurant r = repo.findById(restaurantId);
        if(r!=null){
             r.setOpen(isOpen);
        }
    }



}
