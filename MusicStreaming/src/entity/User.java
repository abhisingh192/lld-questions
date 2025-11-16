package entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {

    private final String id;
    private final String username;

    private final String password;

    private final Set<String> likedSongs = new HashSet<>();
    private final List<String> listeningHistory = new ArrayList<>();
    private final Set<String> followedArtists = new HashSet<>();

    public User(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public boolean checkPassword(String pwd) {
        return this.password.equals(pwd);
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Set<String> getLikedSongs() {
        return likedSongs;
    }
    public List<String> getListeningHistory() {
        return listeningHistory;
    }
    public Set<String> getFollowedArtists() {
        return followedArtists;
    }

}
