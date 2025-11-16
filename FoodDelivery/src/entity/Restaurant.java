package entity;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Restaurant {

    private final String id, name;
    private final Location location;
    private final List<MenuItem> menu = new CopyOnWriteArrayList<>(); // this is the thread safe version of array list
    private boolean isOpen = true;

    //constructors in Java do not have a return type, not even void. T
    public Restaurant(String id, String name, Location loc){
        this.id=id;
        this.name=name;
        this.location=loc;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public List<MenuItem> getMenu() {
        return Collections.unmodifiableList(menu);
    }

    public void addItem(MenuItem m){ menu.add(m); }
    public void removeItem(String itemId){ menu.removeIf(mi -> mi.getId().equals(itemId)); }

    @Override
    public String toString(){ return name+" ("+id+")"; }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

}
