package service;

import entity.Booking;
import entity.Seat;
import entity.SeatType;
import entity.Show;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BookingService {

    private final Lock bookingLock = new ReentrantLock();


    public Booking bookSeats(Show show, List<Integer> seatNumbers, double pricePerSeat) {
        List<Seat> bookedSeats = new ArrayList<>();
        bookingLock.lock(); // prevent race conditions on concurrent seat booking
        try {
            for (int seatNumber : seatNumbers) {
                Seat seat = show.getSeats().get(seatNumber - 1);
                if (seat.isBooked()) {
                    System.out.println("❌ Seat " + seatNumber + " already booked!");
                    return null;
                }
            }

            for (int seatNumber : seatNumbers) {
                Seat seat = show.getSeats().get(seatNumber - 1);
                seat.book();
                bookedSeats.add(seat);
            }
        } finally {
            bookingLock.unlock();
        }

        double total = bookedSeats.stream()
                .mapToDouble(s -> pricePerSeat * (s.getSeatType() == SeatType.PREMIUM ? 1.5 : 1.0))
                .sum();

        return new Booking(UUID.randomUUID().toString(), show, bookedSeats, total);
    }

}
