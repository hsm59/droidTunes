package com.hussainmukadam.droidtunes.data;

import android.provider.BaseColumns;

/**
 * Created by hussain on 7/13/17.
 */

public class FavoritesContract {

    public static final String[] ALL_FAVORITES_LIST = new String[]{
            FavoriteEntry._ID, FavoriteEntry.ARTIST_NAME, FavoriteEntry.TRACK_NAME,
            FavoriteEntry.ARTWORK_URL, FavoriteEntry.TRACK_PRICE, FavoriteEntry.GENRE_NAME,
            FavoriteEntry.TRACK_TIME, FavoriteEntry.COLLECTION_NAME, FavoriteEntry.COLLECTION_VIEW_URL,
            FavoriteEntry.TRACK_VIEW_URL, FavoriteEntry.COLLECTION_PRICE, FavoriteEntry.RELEASE_DATE, FavoriteEntry.PREVIEW_URL
    };

    public static final class FavoriteEntry implements BaseColumns {
        public static final String TABLE_NAME = "favorites";
        public static final String ARTIST_NAME = "artistName";
        public static final String TRACK_NAME = "trackName";
        public static final String ARTWORK_URL = "artworkUrl100";
        public static final String TRACK_PRICE = "trackPrice";
        public static final String GENRE_NAME = "primaryGenreName";
        public static final String TRACK_TIME = "trackTimeMillis";
        public static final String COLLECTION_NAME = "collectionName";
        public static final String COLLECTION_VIEW_URL = "collectionViewUrl";
        public static final String TRACK_VIEW_URL = "trackViewUrl";
        public static final String COLLECTION_PRICE = "collectionPrice";
        public static final String RELEASE_DATE = "releaseDate";
        public static final String PREVIEW_URL = "previewUrl";
    }
}
