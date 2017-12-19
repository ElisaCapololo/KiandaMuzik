package models;

import java.util.ArrayList;

/**
 * Created by Elisa Capololo on 18-12-2017.
 */

public class PopularTrackList {

    private int ptrackListId;
    private int artistpopularId;
    private ArrayList<Track> popularTracks;

    public PopularTrackList(int ptrackListId, int artistpopularId, ArrayList<Track> popularTracks) {
        this.ptrackListId = ptrackListId;
        this.artistpopularId = artistpopularId;
        this.popularTracks = popularTracks;
    }

    public int getPtrackListId() {
        return ptrackListId;
    }

    public void setPtrackListId(int ptrackListId) {
        this.ptrackListId = ptrackListId;
    }

    public int getArtistpopularId() {
        return artistpopularId;
    }

    public void setArtistpopularId(int artistpopularId) {
        this.artistpopularId = artistpopularId;
    }

    public ArrayList<Track> getPopularTracks() {
        return popularTracks;
    }

    public void setPopularTracks(ArrayList<Track> popularTracks) {
        this.popularTracks = popularTracks;
    }
}

