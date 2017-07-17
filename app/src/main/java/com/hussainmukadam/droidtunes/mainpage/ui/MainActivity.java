package com.hussainmukadam.droidtunes.mainpage.ui;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hussainmukadam.droidtunes.R;
import com.hussainmukadam.droidtunes.mainpage.adapters.SongAdapter;
import com.hussainmukadam.droidtunes.mainpage.models.Song;
import com.hussainmukadam.droidtunes.mainpage.models.SongResponse;
import com.hussainmukadam.droidtunes.network.ApiClient;
import com.hussainmukadam.droidtunes.network.ApiInterface;
import com.hussainmukadam.droidtunes.utils.Util;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity implements TextView.OnEditorActionListener {
    private static final String TAG = "MainActivity";
    ProgressDialog mProgressDialog;
    SongAdapter songAdapter;
    String mSongName;
    @BindView(R.id.et_search) EditText et_search;
    @BindView(R.id.rv_songs) RecyclerView rv_songs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupProgressDialog();
        setupRecycler();
        et_search.setOnEditorActionListener(this);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            if (et_search.getText().toString().length() == 0) {
                Toast.makeText(this, "Please Enter Artist Name", Toast.LENGTH_SHORT).show();
            } else if (!Util.isConnected(this)) {
                Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            } else {
                mSongName = et_search.getText().toString().trim().replace(" ", "+");
                Log.d(TAG, "onEditorAction: mSongName " + mSongName);
                mProgressDialog.show();
                performSearch(mSongName);
            }
            return true;
        }
        return false;
    }

    private void performSearch(String artistName) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<SongResponse> call = apiService.getSongDetails(artistName);

        call.enqueue(new Callback<SongResponse>() {
            @Override
            public void onResponse(Call<SongResponse> call, retrofit2.Response<SongResponse> response) {
                Log.d(TAG, "onResponse: Response Code " + response.code());
                if (response.isSuccessful()) {
                    if (response.body().getResults().size() != 0) {
                        Util.hideSoftInput(MainActivity.this);
                        List<Song> mSongsListRetrofit = response.body().getResults();
                        mProgressDialog.dismiss();
                        songAdapter = new SongAdapter(mSongsListRetrofit);
                        rv_songs.setAdapter(songAdapter);
                        songAdapter.notifyDataSetChanged();
                        Log.d(TAG, "onResponse: Songs List " + mSongsListRetrofit.size());
                    } else {
                        mProgressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Could not find any songs..", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Util.hideSoftInput(MainActivity.this);
                    mProgressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Some error occurred, please try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SongResponse> call, Throwable t) {
                mProgressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Some error occurred, please try again", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: Failure Occurred " + t.getMessage());
            }
        });
    }


    private void setupRecycler() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_songs.setLayoutManager(linearLayoutManager);
    }

    //TODO: Move this method to Util, if there are any more network calls
    private void setupProgressDialog() {
        mProgressDialog = new ProgressDialog(MainActivity.this, R.style.ProgressBarTheme);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
    }

}
