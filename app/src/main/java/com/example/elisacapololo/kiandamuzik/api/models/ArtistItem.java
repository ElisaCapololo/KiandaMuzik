package com.example.elisacapololo.kiandamuzik.api.models;


import com.google.gson.annotations.SerializedName;


public class ArtistItem{

	@SerializedName("artist_profile_pic")
	private String artistProfilePic;

	@SerializedName("name")
	private String name;

	@SerializedName("description")
	private String description;

	@SerializedName("artist_banner")
	private String artistBanner;

	public void setArtistProfilePic(String artistProfilePic){
		this.artistProfilePic = artistProfilePic;
	}

	public String getArtistProfilePic(){
		return artistProfilePic;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setArtistBanner(String artistBanner){
		this.artistBanner = artistBanner;
	}

	public String getArtistBanner(){
		return artistBanner;
	}

	@Override
 	public String toString(){
		return 
			"ArtistItem{" + 
			"artist_profile_pic = '" + artistProfilePic + '\'' + 
			",name = '" + name + '\'' + 
			",description = '" + description + '\'' + 
			",artist_banner = '" + artistBanner + '\'' + 
			"}";
		}
}