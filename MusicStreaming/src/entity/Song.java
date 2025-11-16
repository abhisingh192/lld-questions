package entity;

public class Song {

    private final String id;
    private final String title;
    private final String artist;
    private final String album;
    private final int durationSec; // song length in seconds
    private final String genre;
    private final String url; // where song file/stream is hosted

    public Song(String id, String title, String artist, String album, int durationSec, String genre, String url) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.durationSec = durationSec;
        this.genre = genre;
        this.url = url;
    }
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public int getDurationSec() {
        return durationSec;
    }

    public String getGenre() {
        return genre;
    }
    public String getUrl() {
        return url;
    }


    @Override
    public String toString() {
        return title + " - " + artist + " (" + album + ")";
    }


}
