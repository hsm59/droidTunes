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
            FavoriteEntry.COLUMN_PREVIEW_URL
    };

    public static final class FavoriteEntry implements BaseColumns {
        public static final String TABLE_NAME = "favorites";
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
    }
}
