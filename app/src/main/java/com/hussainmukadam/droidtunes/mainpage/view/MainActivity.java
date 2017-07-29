package com.hussainmukadam.droidtunes.mainpage.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Parcelable;
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
import com.hussainmukadam.droidtunes.mainpage.presenter.MainPresenter;
import com.hussainmukadam.droidtunes.utils.Util;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View, TextView.OnEditorActionListener {
    private static final String TAG = "MainActivity";
    ProgressDialog mProgressDialog;
    SongAdapter songAdapter;
    String mArtistName;
    LinearLayoutManager linearLayoutManager;
    Parcelable stateList;
    Bundle stateBundle;
    private static final String RETAIN_STATE = "on_saved_instance_state";
    private MainContract.Presenter mainPagePresenter;
    private MainPresenter mMainPresenter;
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
        mMainPresenter = new MainPresenter(this);
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
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_songs.setLayoutManager(linearLayoutManager);
    }


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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Saving instance of the state in Parcelable stateList
        stateList = rv_songs.getLayoutManager().onSaveInstanceState();
        outState.putParcelable(RETAIN_STATE, stateList);
    }

    @Override
    protected void onPause() {
        super.onPause();

        stateBundle = new Bundle();
        stateList = rv_songs.getLayoutManager().onSaveInstanceState();
        stateBundle.putParcelable(RETAIN_STATE, stateList);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState!=null) {
            et_search.onEditorAction(EditorInfo.IME_ACTION_SEARCH);
            stateList = savedInstanceState.getParcelable(RETAIN_STATE);
            rv_songs.getLayoutManager().onRestoreInstanceState(stateList);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(stateBundle!=null){
            stateList = stateBundle.getParcelable(RETAIN_STATE);
            rv_songs.getLayoutManager().onRestoreInstanceState(stateList);
        }
    }
}
