package entity;

import java.util.List;

public class Booking {

    private String bookingId;
    private Show show;
    private List<Seat> bookedSeats;
    private double totalAmount;

    public Booking(String bookingId, Show show, List<Seat> bookedSeats, double totalAmount) {
        this.bookingId = bookingId;
        this.show = show;
        this.bookedSeats = bookedSeats;
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + bookingId + '\'' +
                ", show=" + show +
                ", bookedSeats=" + bookedSeats +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
