package com.hussainmukadam.droidtunes.mainpage.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hussain on 6/7/17.
 */

public class SongResponse {
    @SerializedName("results")
    private List<Song> results;

    public List<Song> getResults() {
        return results;
    }

    public void setResults(List<Song> results) {
        this.results = results;
    }
}
