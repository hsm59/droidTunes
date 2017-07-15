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
        final String SQL_CREATE_FAVORITE_TABLE = "CREATE TABLE " + FavoriteEntry.TABLE_NAME + " (" +
                FavoriteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                FavoriteEntry.COLUMN_ARTIST_NAME+ " TEXT NOT NULL," +
                FavoriteEntry.COLUMN_TRACK_NAME + " TEXT NOT NULL," +
                FavoriteEntry.COLUMN_ARTWORK_URL + " TEXT NOT NULL," +
                FavoriteEntry.COLUMN_TRACK_PRICE + " TEXT NOT NULL," +
                FavoriteEntry.COLUMN_GENRE_NAME + " TEXT NOT NULL," +
                FavoriteEntry.COLUMN_TRACK_TIME + " TEXT NOT NULL," +
                FavoriteEntry.COLUMN_COLLECTION_NAME + " TEXT NOT NULL," +
                FavoriteEntry.COLUMN_COLLECTION_VIEW_URL + " TEXT NOT NULL," +
                FavoriteEntry.COLUMN_TRACK_VIEW_URL + " TEXT NOT NULL," +
                FavoriteEntry.COLUMN_COLLECTION_PRICE + " TEXT NOT NULL," +
                FavoriteEntry.COLUMN_RELEASE_DATE + " TEXT NOT NULL," +
                FavoriteEntry.COLUMN_PREVIEW_URL + " TEXT NOT NULL," +
                FavoriteEntry.COLUMN_TRACK_ID +" TEXT NOT NULL," +
                FavoriteEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" + ");";

        db.execSQL(SQL_CREATE_FAVORITE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO: Add logic to upgrade the database when version is changed
        db.execSQL("DROP TABLE IF EXISTS " + FavoriteEntry.TABLE_NAME);
        onCreate(db);
    }
}
