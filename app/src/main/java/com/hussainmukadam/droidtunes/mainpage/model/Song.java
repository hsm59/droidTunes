package com.hussainmukadam.droidtunes.mainpage.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hussain on 6/10/17.
 */

public class Song implements Parcelable{
    @SerializedName("artistName")
    private String artistName;
    @SerializedName("trackName")
    private String trackName;
    @SerializedName("artworkUrl30")
    private String artworkUrl30;
    @SerializedName("artworkUrl100")
    private String artworkUrl100;
    @SerializedName("trackPrice")
    private String trackPrice;
    @SerializedName("trackTimeMillis")
    private String trackTimeMillis;
    @SerializedName("collectionName")
    private String collectionName;
    @SerializedName("collectionViewUrl")
    private String collectionViewUrl;
    @SerializedName("trackViewUrl")
    private String trackViewUrl;
    @SerializedName("collectionPrice")
    private String collectionPrice;
    @SerializedName("releaseDate")
    private String releaseDate;
    @SerializedName("previewUrl")
    private String previewUrl;
    @SerializedName("primaryGenreName")
    private String primaryGenreName;
    @SerializedName("trackId")
    private String trackId;

    public Song(){}

//    public Song(String artistName, String trackName, String artworkUrl30, String artworkUrl100,
//                String trackPrice, String trackTimeMillis, String collectionName, String collectionViewUrl,
//                String trackViewUrl, String collectionPrice, String releaseDate, String previewUrl,
//                String primaryGenreName, String trackId){
//        this.artistName = artistName;
//        this.trackName = trackName;
//        this.artworkUrl30 = artworkUrl30;
//        this.artworkUrl100 = artworkUrl100;
//        this.trackPrice = trackPrice;
//        this.trackTimeMillis = trackTimeMillis;
//        this.collectionName = collectionName;
//        this.collectionViewUrl = collectionViewUrl;
//        this.trackViewUrl = trackViewUrl;
//        this.collectionPrice = collectionPrice;
//        this.releaseDate = releaseDate;
//        this.previewUrl = previewUrl;
//        this.primaryGenreName = primaryGenreName;
//        this.trackId = trackId;
//    }

    private Song(Parcel in){
        artistName = in.readString();
        trackName = in.readString();
        artworkUrl30 = in.readString();
        artworkUrl100 = in.readString();
        trackPrice = in.readString();
        trackTimeMillis = in.readString();
        primaryGenreName = in.readString();
        collectionName = in.readString();
        collectionViewUrl = in.readString();
        trackViewUrl = in.readString();
        collectionPrice = in.readString();
        releaseDate = in.readString();
        previewUrl = in.readString();
        trackId = in.readString();
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getArtworkUrl30() {
        return artworkUrl30;
    }

    public void setArtworkUrl30(String artworkUrl30) {
        this.artworkUrl30 = artworkUrl30;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }

    public String getTrackPrice() {
        return trackPrice;
    }

    public void setTrackPrice(String trackPrice) {
        this.trackPrice = trackPrice;
    }

    public String getTrackTimeMillis() {
        return trackTimeMillis;
    }

    public void setTrackTimeMillis(String trackTimeMillis) {
        this.trackTimeMillis = trackTimeMillis;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public void setPrimaryGenreName(String primaryGenreName) {
        this.primaryGenreName = primaryGenreName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getCollectionViewUrl() {
        return collectionViewUrl;
    }

    public void setCollectionViewUrl(String collectionViewUrl) {
        this.collectionViewUrl = collectionViewUrl;
    }

    public String getTrackViewUrl() {
        return trackViewUrl;
    }

    public void setTrackViewUrl(String trackViewUrl) {
        this.trackViewUrl = trackViewUrl;
    }

    public String getCollectionPrice() {
        return collectionPrice;
    }

    public void setCollectionPrice(String collectionPrice) {
        this.collectionPrice = collectionPrice;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(artistName);
        dest.writeString(trackName);
        dest.writeString(artworkUrl30);
        dest.writeString(artworkUrl100);
        dest.writeString(trackPrice);
        dest.writeString(primaryGenreName);
        dest.writeString(trackTimeMillis);
        dest.writeString(collectionName);
        dest.writeString(collectionViewUrl);
        dest.writeString(trackViewUrl);
        dest.writeString(collectionPrice);
        dest.writeString(releaseDate);
        dest.writeString(previewUrl);
        dest.writeString(trackId);
    }

    public static final Parcelable.Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel source) {
            return new Song(source);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };
}
