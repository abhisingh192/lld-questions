package repo;

import entity.Theater;

import java.util.ArrayList;
import java.util.List;

public class TheaterRepository {

    private final List<Theater> theaters = new ArrayList<>();

    public void addTheater(Theater theater) {
        theaters.add(theater);
    }

    public List<Theater> getAllTheaters() {
        return theaters;
    }
}
