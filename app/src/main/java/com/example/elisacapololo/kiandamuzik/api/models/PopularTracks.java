package com.example.elisacapololo.kiandamuzik.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class PopularTracks implements Parcelable {

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

	protected PopularTracks(Parcel in) {
		trackCoverArt = in.readString();
		trackUrl = in.readString();
		trackTitle = in.readString();
		views = in.readInt();
	}

	public static final Creator<PopularTracks> CREATOR = new Creator<PopularTracks>() {
		@Override
		public PopularTracks createFromParcel(Parcel in) {
			return new PopularTracks(in);
		}

		@Override
		public PopularTracks[] newArray(int size) {
			return new PopularTracks[size];
		}
	};

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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(trackCoverArt);
		dest.writeString(trackUrl);
		dest.writeString(trackTitle);
		dest.writeInt(views);
	}
}