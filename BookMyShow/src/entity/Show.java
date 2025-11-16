package entity;

import java.time.LocalDateTime;
import java.util.List;

public class Show {

    private String showId;
    private Movie movie;
    private LocalDateTime showTime;
    private List<Seat> seats;

    public Show(String showId, Movie movie, LocalDateTime showTime, List<Seat> seats) {
        this.showId = showId;
        this.movie = movie;
        this.showTime = showTime;
        this.seats = seats;
    }

    public String getShowId() {
        return showId;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void displaySeats() {
        System.out.println("\nSeat Availability for " + movie.getTitle() + " (" + showTime + "):");
        for (Seat s : seats) System.out.println(s);
    }
}
