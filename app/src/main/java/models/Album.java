package models;

/**
 * Created by Elisa Capololo on 08-12-2017.
 */

public class Album {
    private int id;
    private  String name;
    private int artistId;
    private String releaseDate;
    private String price;

    public Album(int id, String name, int artistId, String releaseDate, String price) {
        this.id = id;
        this.name = name;
        this.artistId = artistId;
        this.releaseDate = releaseDate;
        this.price = price;
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

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
