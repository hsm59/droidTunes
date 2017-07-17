package com.hussainmukadam.droidtunes.network;

import com.hussainmukadam.droidtunes.mainpage.model.SongResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by hussain on 6/7/17.
 */

public interface ApiInterface {
    @GET("search")
    Call<SongResponse> getSongDetails(@Query("term") String songName);
}
