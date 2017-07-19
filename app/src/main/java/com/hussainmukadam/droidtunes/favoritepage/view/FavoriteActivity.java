package com.hussainmukadam.droidtunes.favoritepage.view;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hussainmukadam.droidtunes.R;
import com.hussainmukadam.droidtunes.data.FavoritesContract;
import com.hussainmukadam.droidtunes.data.FavoritesDbHandler;
import com.hussainmukadam.droidtunes.favoritepage.FavoritesMVPContract;
import com.hussainmukadam.droidtunes.favoritepage.adapter.FavoriteSongAdapter;
import com.hussainmukadam.droidtunes.favoritepage.presenter.FavoritePresenter;
import com.hussainmukadam.droidtunes.mainpage.model.Song;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hussain on 7/15/17.
 */

public class FavoriteActivity extends AppCompatActivity implements FavoritesMVPContract.View{
    @BindView(R.id.rv_favorite_songs)
    RecyclerView mRvFavoriteSongs;
    FavoriteSongAdapter favoriteSongAdapter;
    private SQLiteDatabase mDb;
    FavoritesMVPContract.Presenter favoritesPresenter;
    FavoritePresenter presenter;
    List<Song> mFavoriteSongsList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Favorites");
        setContentView(R.layout.activity_favorite);
        ButterKnife.bind(this);
        mFavoriteSongsList = new ArrayList<>();
        presenter = new FavoritePresenter(this);

        FavoritesDbHandler favDbHelper = new FavoritesDbHandler(this);
        mDb = favDbHelper.getReadableDatabase();

        presenter.fetchDataFromDatabase(mDb);
    }

    @Override
    public void setPresenter(FavoritesMVPContract.Presenter presenter) {
        this.favoritesPresenter = presenter;
    }

    @Override
    public void showProgress(boolean b) {

    }

    @Override
    public void displayList(List<Song> songsList) {
        mRvFavoriteSongs.setLayoutManager(new GridLayoutManager(this, 3));
        favoriteSongAdapter = new FavoriteSongAdapter(songsList);
        mRvFavoriteSongs.setAdapter(favoriteSongAdapter);
        favoriteSongAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String errorMessage) {

    }
}
