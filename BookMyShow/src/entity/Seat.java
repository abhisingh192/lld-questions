package entity;

public class Seat {
    private int seatNumber;
    private SeatType seatType;
    private boolean isBooked;

    public Seat(int seatNumber, SeatType seatType) {
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.isBooked = false;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void bookSeat() {
        this.isBooked = true;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seatNumber='" + seatNumber + '\'' +
                ", seatType=" + seatType +
                ", isBooked=" + isBooked +
                '}';
    }

    public synchronized boolean book() {
        if (isBooked) return false;
        isBooked = true;
        return true;
    }
}
