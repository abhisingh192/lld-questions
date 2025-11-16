package service;

import entity.Song;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class PlaybackService {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

    public void play(Song song) {
        System.out.println("▶️ Playing: " + song);
    }

    public void pause(Song song) {
        System.out.println("⏸️ Paused: " + song.getTitle());
    }

    public void skip(Song next) {
        System.out.println("⏭️ Skipped to: " + next.getTitle());
    }

    public void seek(Song song, int seconds) {
        System.out.println("⏩ Seeked to " + seconds + "s in " + song.getTitle());
    }
}
