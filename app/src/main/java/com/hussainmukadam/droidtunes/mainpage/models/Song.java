package com.hussainmukadam.droidtunes.mainpage.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hussain on 6/10/17.
 */

public class Song implements Parcelable{
    String artistName;
    String trackName;
    String artworkUrl30;
    String artworkUrl100;
    String trackPrice;
    String trackTimeMillis;
    String primaryGenreName;

    public Song(){}

    private Song(Parcel in){
        artistName = in.readString();
        trackName = in.readString();
        artworkUrl30 = in.readString();
        artworkUrl100 = in.readString();
        trackPrice = in.readString();
        trackTimeMillis = in.readString();
        primaryGenreName = in.readString();
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
