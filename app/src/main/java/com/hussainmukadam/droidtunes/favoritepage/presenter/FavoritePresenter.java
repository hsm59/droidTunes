package com.hussainmukadam.droidtunes.favoritepage.presenter;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hussainmukadam.droidtunes.data.FavoritesContract;
import com.hussainmukadam.droidtunes.favoritepage.FavoritesMVPContract;
import com.hussainmukadam.droidtunes.favoritepage.FavoritesMVPContract.Presenter;
import com.hussainmukadam.droidtunes.mainpage.model.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hussain on 7/19/17.
 */

public class FavoritePresenter implements Presenter {
    FavoritesMVPContract.View mView;

    public FavoritePresenter(FavoritesMVPContract.View view) {
        this.mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void fetchDataFromDatabase(SQLiteDatabase mDb) {
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
        mView.displayList(mFavoriteSongsList);
    }


    private Song cursorToSong(Cursor cursor) {
        Song song = new Song();
        song.setArtistName(cursor.getString(FavoritesContract.COL_ARTIST_NAME));
        song.setArtworkUrl100(cursor.getString(FavoritesContract.COL_ARTWORK_URL));
        song.setCollectionName(cursor.getString(FavoritesContract.COL_COLLECTION_NAME));
        song.setCollectionPrice(cursor.getString(FavoritesContract.COL_COLLECTION_PRICE));
        song.setCollectionViewUrl(cursor.getString(FavoritesContract.COL_COLLECTION_VIEW_URL));
        song.setPreviewUrl(cursor.getString(FavoritesContract.COL_PREVIEW_URL));
        song.setPrimaryGenreName(cursor.getString(FavoritesContract.COL_GENRE_NAME));
        song.setReleaseDate(cursor.getString(FavoritesContract.COL_RELEASE_DATE));
        song.setTrackId(cursor.getString(FavoritesContract.COL_TRACK_ID));
        song.setTrackName(cursor.getString(FavoritesContract.COL_TRACK_NAME));
        song.setTrackPrice(cursor.getString(FavoritesContract.COL_TRACK_PRICE));
        song.setTrackTimeMillis(cursor.getString(FavoritesContract.COL_TRACK_TIME));
        song.setTrackViewUrl(cursor.getString(FavoritesContract.COL_TRACK_VIEW_URL));
        return song;
    }
}
