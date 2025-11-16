package entity;

import java.util.ArrayList;
import java.util.List;

public class Theater {
    private String theaterId;
    private String name;
    private String location;

    private List<Show> shows = new ArrayList<>();

    public Theater(String theaterId, String name, String location) {
        this.theaterId = theaterId;
        this.name = name;
        this.location = location;
    }

    public String getTheaterId() {
        return theaterId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public List<Show> getShows() {
        return shows;
    }

    public void addShow(Show show) {
        shows.add(show);
    }

    @Override
    public String toString() {
        return "Theater{" +
                "theaterId='" + theaterId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
