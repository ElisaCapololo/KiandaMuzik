package models;

/**
 * Created by Elisa Capololo on 08-12-2017.
 */

public class Artista {
    private int id;
    private String name;
    private String description;
    private String musicStyle;
    private int artistCoverImage;
    private boolean verified;

    public Artista(int id, String name, String description, String musicStyle, int artistCoverImage, boolean verified) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.musicStyle = musicStyle;
        this.artistCoverImage = artistCoverImage;
        this.verified = verified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMusicStyle() {
        return musicStyle;
    }

    public void setMusicStyle(String musicStyle) {
        this.musicStyle = musicStyle;
    }

    public int getArtistCoverImage() {
        return artistCoverImage;
    }

    public void setArtistCoverImage(int artistCoverImage) {
        this.artistCoverImage = artistCoverImage;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
