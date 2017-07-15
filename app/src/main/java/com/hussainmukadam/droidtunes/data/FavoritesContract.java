package com.hussainmukadam.droidtunes.data;

import android.provider.BaseColumns;

/**
 * Created by hussain on 7/13/17.
 */

public class FavoritesContract {

    public static final String[] ALL_FAVORITES_LIST = new String[]{
            FavoriteEntry._ID, FavoriteEntry.COLUMN_ARTIST_NAME, FavoriteEntry.COLUMN_TRACK_NAME,
            FavoriteEntry.COLUMN_ARTWORK_URL, FavoriteEntry.COLUMN_TRACK_PRICE, FavoriteEntry.COLUMN_GENRE_NAME,
            FavoriteEntry.COLUMN_TRACK_TIME, FavoriteEntry.COLUMN_COLLECTION_NAME, FavoriteEntry.COLUMN_COLLECTION_VIEW_URL,
            FavoriteEntry.COLUMN_TRACK_VIEW_URL, FavoriteEntry.COLUMN_COLLECTION_PRICE, FavoriteEntry.COLUMN_RELEASE_DATE,
            FavoriteEntry.COLUMN_PREVIEW_URL, FavoriteEntry.COLUMN_TRACK_ID, FavoriteEntry.COLUMN_TIMESTAMP
    };

    public static final int COL_ID = 0;
    public static final int COL_ARTIST_NAME = 1;
    public static final int COL_TRACK_NAME = 2;
    public static final int COL_ARTWORK_URL = 3;
    public static final int COL_TRACK_PRICE = 4;
    public static final int COL_GENRE_NAME = 5;
    public static final int COL_TRACK_TIME = 6;
    public static final int COL_COLLECTION_NAME = 7;
    public static final int COL_COLLECTION_VIEW_URL = 8;
    public static final int COL_TRACK_VIEW_URL = 8;
    public static final int COL_COLLECTION_PRICE = 10;
    public static final int COL_RELEASE_DATE = 11;
    public static final int COL_PREVIEW_URL = 12;
    public static final int COL_TRACK_ID = 13;
    public static final int COL_TIMESTAMP = 14;

    public static final class FavoriteEntry implements BaseColumns {
        public static final String TABLE_NAME = "favorites";
        public static final String COLUMN_TRACK_ID = "trackId";
        public static final String COLUMN_ARTIST_NAME = "artistName";
        public static final String COLUMN_TRACK_NAME = "trackName";
        public static final String COLUMN_ARTWORK_URL = "artworkUrl100";
        public static final String COLUMN_TRACK_PRICE = "trackPrice";
        public static final String COLUMN_GENRE_NAME = "primaryGenreName";
        public static final String COLUMN_TRACK_TIME = "trackTimeMillis";
        public static final String COLUMN_COLLECTION_NAME = "collectionName";
        public static final String COLUMN_COLLECTION_VIEW_URL = "collectionViewUrl";
        public static final String COLUMN_TRACK_VIEW_URL = "trackViewUrl";
        public static final String COLUMN_COLLECTION_PRICE = "collectionPrice";
        public static final String COLUMN_RELEASE_DATE = "releaseDate";
        public static final String COLUMN_PREVIEW_URL = "previewUrl";
        public static final String COLUMN_TIMESTAMP = "timeAdded";
    }
}
