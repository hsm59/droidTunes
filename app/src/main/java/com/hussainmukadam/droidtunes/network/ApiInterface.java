package com.hussainmukadam.droidtunes.network;

import com.hussainmukadam.droidtunes.mainpage.models.Song;
import com.hussainmukadam.droidtunes.mainpage.models.SongResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by hussain on 6/7/17.
 */

public interface ApiInterface {
    @GET("/{songname}")
    Call<SongResponse> getSongDetails(@Path("songname") String songName);
}
