package repo;

import entity.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SongRepo {

    private final Map<String, Song> songStorage = new ConcurrentHashMap<>();

    public void addSong(Song song) {
        songStorage.put(song.getId(), song);
    }

    public Song getSongById(String id) {
        return songStorage.get(id);
    }

    public void removeSongById(String id) {
        songStorage.remove(id);
    }

    public List<Song> searchSongsByKeyword(String keyword) {
        return songStorage.values().stream()
                .filter(song -> song.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                                song.getArtist().toLowerCase().contains(keyword.toLowerCase()) ||
                                song.getAlbum().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }

    public List<Song> all() { return new ArrayList<>(songStorage.values()); }

}
