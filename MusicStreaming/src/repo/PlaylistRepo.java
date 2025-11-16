package repo;

import entity.Playlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PlaylistRepo {

    private final Map<String, Playlist> playlistStorage = new ConcurrentHashMap<>();

    public void addPlaylist(Playlist playlist) {
        playlistStorage.put(playlist.getId(), playlist);
    }

    public List<Playlist> findByUser(String userId) {
        List<Playlist> result = new ArrayList<>();
        for(Playlist p : playlistStorage.values()) {
            if(p.getUserId().equals(userId)) result.add(p);
        }
        return result;
    }


}
