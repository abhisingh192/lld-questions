package service;

import entity.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AdminService {

    public Show createShow(Movie movie, LocalDateTime time, int totalSeats) {
        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= totalSeats; i++) {
            SeatType type = (i <= 5) ? SeatType.PREMIUM : SeatType.REGULAR;
            seats.add(new Seat(i, type));
        }
        return new Show(UUID.randomUUID().toString(), movie, time, seats);
    }

    public void addShowToTheater(Theater theater, Show show) {
        theater.addShow(show);
        System.out.println("✅ Added show of " + show.getMovie().getTitle() + " to " + theater.getName());
    }
}
