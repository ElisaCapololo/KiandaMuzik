package com.example.elisacapololo.kiandamuzik.api.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class PopularTracks {

	@SerializedName("trackCoverArt")
	private String trackCoverArt;

	@SerializedName("artist")
	private List<ArtistItem> artist;

	@SerializedName("trackUrl")
	private String trackUrl;

	@SerializedName("trackTitle")
	private String trackTitle;

	@SerializedName("views")
	private int views;

	public void setTrackCoverArt(String trackCoverArt){
		this.trackCoverArt = trackCoverArt;
	}

	public String getTrackCoverArt(){
		return trackCoverArt;
	}

	public void setArtist(List<ArtistItem> artist){
		this.artist = artist;
	}

	public List<ArtistItem> getArtist(){
		return artist;
	}

	public void setTrackUrl(String trackUrl){
		this.trackUrl = trackUrl;
	}

	public String getTrackUrl(){
		return trackUrl;
	}

	public void setTrackTitle(String trackTitle){
		this.trackTitle = trackTitle;
	}

	public String getTrackTitle(){
		return trackTitle;
	}

	public void setViews(int views){
		this.views = views;
	}

	public int getViews(){
		return views;
	}

	@Override
 	public String toString(){
		return 
			"PopularTracks{" +
			"trackCoverArt = '" + trackCoverArt + '\'' + 
			",artist = '" + artist + '\'' + 
			",trackUrl = '" + trackUrl + '\'' + 
			",trackTitle = '" + trackTitle + '\'' + 
			",views = '" + views + '\'' + 
			"}";
		}
}