package com.hussainmukadam.droidtunes.mainpage.presenter;

import com.hussainmukadam.droidtunes.mainpage.MainContract;
import com.hussainmukadam.droidtunes.mainpage.model.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hussain on 17/7/17.
 */

public class MainPresenter implements MainContract.Presenter {
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
    public void fetchSongsListFromServer(String songName) {

    }
}
