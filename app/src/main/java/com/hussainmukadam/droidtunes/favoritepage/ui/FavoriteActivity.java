package com.hussainmukadam.droidtunes.favoritepage.ui;

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
import com.hussainmukadam.droidtunes.favoritepage.adapters.FavoriteSongAdapter;
import com.hussainmukadam.droidtunes.mainpage.models.Song;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hussain on 7/15/17.
 */

public class FavoriteActivity extends AppCompatActivity {
    @BindView(R.id.rv_favorite_songs)
    RecyclerView mRvFavoriteSongs;
    FavoriteSongAdapter favoriteSongAdapter;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        ButterKnife.bind(this);

        FavoritesDbHandler favDbHelper = new FavoritesDbHandler(this);
        mDb = favDbHelper.getReadableDatabase();


        mRvFavoriteSongs.setLayoutManager(new GridLayoutManager(this, 3));
        favoriteSongAdapter = new FavoriteSongAdapter(getFavoriteSongsList());
        mRvFavoriteSongs.setAdapter(favoriteSongAdapter);
    }

    private List<Song> getFavoriteSongsList(){
        List<Song> mFavoriteSongsList = new ArrayList<>();


        Cursor mCursor = mDb.query(FavoritesContract.FavoriteEntry.TABLE_NAME,
                FavoritesContract.ALL_FAVORITES_LIST, null, null, null, null,
                FavoritesContract.FavoriteEntry.COLUMN_TIMESTAMP);

        mCursor.moveToFirst();

        while(!mCursor.isAfterLast()){
            Song song = cursorToSong(mCursor);
            mFavoriteSongsList.add(song);
            mCursor.moveToNext();
        }

        mCursor.close();
        return mFavoriteSongsList;
    }

    private Song cursorToSong(Cursor cursor) {
        Song song = new Song();
        song.setArtworkUrl100(cursor.getString(FavoritesContract.COL_ARTWORK_URL));
        return song;
    }

}
