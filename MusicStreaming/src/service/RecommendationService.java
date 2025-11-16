package service;

import entity.Song;
import entity.User;
import repo.SongRepo;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RecommendationService {

    private final SongRepo songRepo;
    private final Random rand = new Random();

    public RecommendationService(SongRepo songRepo) {
        this.songRepo = songRepo;
    }
    public List<Song> recommend(User user) {
        if(user.getLikedSongs().isEmpty() && user.getListeningHistory().isEmpty())
            return randomSongs();

        // Very simple heuristic: recommend same artist or genre
        List<Song> all = songRepo.all();
        return all.stream()
                .filter(s -> user.getLikedSongs().contains(s.getId())
                        || user.getFollowedArtists().contains(s.getArtist())
                        || user.getListeningHistory().stream().anyMatch(h -> s.getTitle().equalsIgnoreCase(h)))
                .limit(5)
                .collect(Collectors.toList());
    }

    private List<Song> randomSongs() {
        List<Song> all = songRepo.all();
        Collections.shuffle(all);
        return all.subList(0, Math.min(5, all.size()));
    }
}
