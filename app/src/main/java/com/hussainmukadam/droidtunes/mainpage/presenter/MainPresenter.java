package com.hussainmukadam.droidtunes.mainpage.presenter;

import android.util.Log;

import com.hussainmukadam.droidtunes.mainpage.MainContract;
import com.hussainmukadam.droidtunes.mainpage.model.Song;
import com.hussainmukadam.droidtunes.mainpage.model.SongResponse;
import com.hussainmukadam.droidtunes.network.ApiClient;
import com.hussainmukadam.droidtunes.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by hussain on 17/7/17.
 */

public class MainPresenter implements MainContract.Presenter {
    private static final String TAG = "MainPresenter";
    private MainContract.View mView;
    List<Song> mSongsList;

    public MainPresenter(MainContract.View view){
        mView = view;
        mView.setPresenter(this);
        mSongsList = new ArrayList<>();
    }


    @Override
    public void start() {

    }

    @Override
    public void fetchSongsListFromServer(String artistName) {
        mView.showProgressBar();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<SongResponse> call = apiService.getSongDetails(artistName);

        call.enqueue(new Callback<SongResponse>() {
            @Override
            public void onResponse(Call<SongResponse> call, retrofit2.Response<SongResponse> response) {
                Log.d(TAG, "onResponse: Response Code " + response.code());
                if (response.isSuccessful()) {
                    if (response.body().getResults().size() != 0) {
                        mView.hideProgressBar();
                        List<Song> mSongsListRetrofit = response.body().getResults();
                        mView.showSongsList(mSongsListRetrofit);
                        Log.d(TAG, "onResponse: Songs List " + mSongsListRetrofit.size());
                    } else {
                        mView.showError("Couldn't find any songs");
                        mView.hideProgressBar();
                    }
                } else {
                    mView.showError("Some Error Occurred");
                    mView.hideProgressBar();
                }
            }

            @Override
            public void onFailure(Call<SongResponse> call, Throwable t) {
                mView.hideProgressBar();
                mView.showError(t.getMessage());
                Log.d(TAG, "onFailure: Failure Occurred " + t.getMessage());
            }
        });
    }
}
