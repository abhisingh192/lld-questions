package service;

import entity.Show;
import entity.Theater;

import java.util.List;

public class MovieService {

    public void displayMovies(List<Theater> theaters) {
        System.out.println("\n🎬 Movies Currently Playing:");
        for (Theater theater : theaters) {
            System.out.println("\nTheater: " + theater.getName());
            for (Show show : theater.getShows()) {
                System.out.println(" - " + show.getMovie().getTitle() + " at " + show.getShowTime());
            }
        }
    }
}
