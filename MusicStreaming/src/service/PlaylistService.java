package service;

import entity.Playlist;
import entity.Song;
import repo.PlaylistRepo;

import java.util.List;
import java.util.UUID;

public class PlaylistService {

    private final PlaylistRepo repo;

    public PlaylistService(PlaylistRepo repo) {
        this.repo = repo;
    }

    public Playlist createPlaylist(String userId, String name) {
        Playlist p = new Playlist(UUID.randomUUID().toString(), userId, name);
        repo.addPlaylist(p);
        System.out.println("🎵 Playlist created: " + name);
        return p;
    }

    public void addSong(Playlist p, Song s) {
        p.addSong(s);
        System.out.println("➕ Added " + s.getTitle() + " to playlist " + p.getName());
    }

    public void removeSong(Playlist p, String songId) {
        p.removeSong(songId);
        System.out.println("❌ Removed song " + songId + " from " + p.getName());
    }

    public List<Playlist> getUserPlaylists(String userId) {
        return repo.findByUser(userId);
    }

}
