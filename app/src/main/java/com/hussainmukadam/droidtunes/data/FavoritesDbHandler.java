package com.hussainmukadam.droidtunes.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.hussainmukadam.droidtunes.data.FavoritesContract.FavoriteEntry;
/**
 * Created by hussain on 7/13/17.
 */

public class FavoritesDbHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "favorites.db";
    private static final int DATABASE_VERSION = 1;

    public FavoritesDbHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_FAVORITE_TABLE = "CREATE TABLE" + FavoriteEntry.TABLE_NAME + " (" +
                FavoriteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                FavoriteEntry.ARTIST_NAME + " TEXT NOT NULL," +
                FavoriteEntry.TRACK_NAME + " TEXT NOT NULL," +
                FavoriteEntry.ARTWORK_URL + " TEXT NOT NULL," +
                FavoriteEntry.TRACK_PRICE + " TEXT NOT NULL," +
                FavoriteEntry.GENRE_NAME + " TEXT NOT NULL," +
                FavoriteEntry.TRACK_TIME + " TEXT NOT NULL," +
                FavoriteEntry.COLLECTION_NAME + " TEXT NOT NULL," +
                FavoriteEntry.COLLECTION_VIEW_URL + " TEXT NOT NULL," +
                FavoriteEntry.TRACK_VIEW_URL + " TEXT NOT NULL," +
                FavoriteEntry.COLLECTION_PRICE + " TEXT NOT NULL," +
                FavoriteEntry.RELEASE_DATE + " TEXT NOT NULL," +
                FavoriteEntry.PREVIEW_URL + " TEXT NOT NULL" + ");";

        db.execSQL(SQL_CREATE_FAVORITE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO: Add logic to upgrade the database when version is changed
        db.execSQL("DROP TABLE IF EXISTS " + FavoriteEntry.TABLE_NAME);
        onCreate(db);
    }
}
