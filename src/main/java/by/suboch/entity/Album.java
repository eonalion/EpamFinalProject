package by.suboch.entity;

import java.util.Date;

/**
 *
 */
public class Album {
    private int albumId;
    private int artistId;
    private String title;
    private int tracksAmount;
    private Date releaseDate;
    private byte[] image;

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTracksAmount() {
        return tracksAmount;
    }

    public void setTracksAmount(int tracksAmount) {
        this.tracksAmount = tracksAmount;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
