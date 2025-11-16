import entity.Booking;
import entity.Movie;
import entity.Show;
import entity.Theater;
import repo.TheaterRepository;
import service.AdminService;
import service.BookingService;
import service.MovieService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Admin setup
        AdminService adminService = new AdminService();
        MovieService movieService = new MovieService();
        BookingService bookingService = new BookingService();
        TheaterRepository repo = new TheaterRepository();

        Movie movie1 = new Movie("M001", "Inception", "Comedy", 148);
        Movie movie2 = new Movie("M002", "Interstellar", "Action", 169);

        Theater pvr = new Theater("T001", "PVR Cinemas","Bengaluru");
        Theater inox = new Theater("T002", "INOX", "Mumbai");

        Show show1 = adminService.createShow(movie1, LocalDateTime.now().plusMinutes(10), 10);
        Show show2 = adminService.createShow(movie2, LocalDateTime.now().plusMinutes(30), 8);

        adminService.addShowToTheater(pvr, show1);
        adminService.addShowToTheater(inox, show2);

        repo.addTheater(pvr);
        repo.addTheater(inox);

        // User flow
        movieService.displayMovies(repo.getAllTheaters());

        // Choose theater + show
        Show selectedShow = pvr.getShows().get(0);
        selectedShow.displaySeats();

        // Book seats
        List<Integer> chosenSeats = Arrays.asList(2, 3);
        Booking booking = bookingService.bookSeats(selectedShow, chosenSeats, 200.0);
        if (booking != null) {
            System.out.println("\n🎟 Booking Successful:\n" + booking);
        }

        // Concurrent booking simulation
        Thread user1 = new Thread(() -> bookingService.bookSeats(selectedShow, Arrays.asList(4, 5), 200.0));
        Thread user2 = new Thread(() -> bookingService.bookSeats(selectedShow, Arrays.asList(5, 6), 200.0));

        user1.start();
        user2.start();
    }
}