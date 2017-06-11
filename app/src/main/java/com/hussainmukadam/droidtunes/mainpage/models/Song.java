package com.hussainmukadam.droidtunes.mainpage.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hussain on 6/10/17.
 */

public class Song implements Parcelable{
    private String artistName;
    private String trackName;
    private String artworkUrl30;
    private String artworkUrl100;
    private String trackPrice;
    private String trackTimeMillis;
    private String collectionName;
    private String collectionViewUrl;
    private String trackViewUrl;
    private String collectionPrice;
    private String releaseDate;
    private String previewUrl;
    private String primaryGenreName;

    public Song(){}

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
