package com.hussainmukadam.droidtunes.mainpage.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hussainmukadam.droidtunes.R;
import com.hussainmukadam.droidtunes.favoritepage.view.FavoriteActivity;
import com.hussainmukadam.droidtunes.mainpage.MainContract;
import com.hussainmukadam.droidtunes.mainpage.adapter.SongAdapter;
import com.hussainmukadam.droidtunes.mainpage.model.Song;
import com.hussainmukadam.droidtunes.mainpage.model.SongResponse;
import com.hussainmukadam.droidtunes.mainpage.presenter.MainPresenter;
import com.hussainmukadam.droidtunes.network.ApiClient;
import com.hussainmukadam.droidtunes.network.ApiInterface;
import com.hussainmukadam.droidtunes.utils.Util;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity implements MainContract.View, TextView.OnEditorActionListener {
    private static final String TAG = "MainActivity";
    ProgressDialog mProgressDialog;
    SongAdapter songAdapter;
    String mArtistName;
    private MainContract.Presenter mainPagePresenter;
    private MainPresenter mMainPresenter;
    @BindView(R.id.et_search) EditText et_search;
    @BindView(R.id.rv_songs) RecyclerView rv_songs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mMainPresenter = new MainPresenter(this);
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
                mArtistName = et_search.getText().toString().trim().replace(" ", "+");
                Log.d(TAG, "onEditorAction: mSongName " + mArtistName);
                mMainPresenter.fetchSongsListFromServer(mArtistName);
            }
            return true;
        }
        return false;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_screen_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_favorite:
                Intent favoriteIntent = new Intent(this, FavoriteActivity.class);
                startActivity(favoriteIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.mainPagePresenter = presenter;
    }

    @Override
    public void showSongsList(List<Song> songsList) {
        Util.hideSoftInput(this);
        songAdapter = new SongAdapter(songsList);
        rv_songs.setAdapter(songAdapter);
        songAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressBar() {
        mProgressDialog.show();
    }

    @Override
    public void hideProgressBar() {
        mProgressDialog.dismiss();
    }

    @Override
    public void showError(String errorMessage) {
        Util.hideSoftInput(this);
        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }
}
