package com.hussainmukadam.droidtunes.favoritepage.view;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hussainmukadam.droidtunes.R;
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
 * Created by hussain on 8/2/17.
 */

public class FavoriteFragment extends Fragment implements FavoritesMVPContract.View{
    @BindView(R.id.rv_favorite_songs)
    RecyclerView mRvFavoriteSongs;
    FavoriteSongAdapter favoriteSongAdapter;
    private SQLiteDatabase mDb;
    FavoritesMVPContract.Presenter favoritesPresenter;
    FavoritePresenter presenter;
    List<Song> mFavoriteSongsList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        //TODO: Add a toolbar and set the title as Favorites
        ButterKnife.bind(this, view);

        mFavoriteSongsList = new ArrayList<>();
        presenter = new FavoritePresenter(this);

        FavoritesDbHandler favDbHelper = new FavoritesDbHandler(getContext());
        mDb = favDbHelper.getReadableDatabase();

        presenter.fetchDataFromDatabase(mDb);

        return view;
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
        mRvFavoriteSongs.setLayoutManager(new GridLayoutManager(getContext(), 3));
        favoriteSongAdapter = new FavoriteSongAdapter(songsList);
        mRvFavoriteSongs.setAdapter(favoriteSongAdapter);
        favoriteSongAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String errorMessage) {

    }
}
