package entity;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

    private final String id;
    private final String userId;
    private final String name;
    private final List<Song> songs = new ArrayList<>();

    public Playlist(String id, String userId, String name) {
        this.id = id;
        this.userId = userId;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSong(String songId) {
        songs.removeIf(s -> s.getId().equals(songId));
    }


}
