package com.hussainmukadam.droidtunes.detailpage.view;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hussainmukadam.droidtunes.BuildConfig;
import com.hussainmukadam.droidtunes.R;
import com.hussainmukadam.droidtunes.data.FavoritesContract;
import com.hussainmukadam.droidtunes.data.FavoritesDbHandler;
import com.hussainmukadam.droidtunes.mainpage.model.Song;
import com.hussainmukadam.droidtunes.utils.MyBasePicassoTargetUtil;
import com.hussainmukadam.droidtunes.utils.Util;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hussain on 7/29/17.
 */

public class DetailFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "DetailFragment";
    @BindView(R.id.iv_detail_artwork) ImageView iv_detail_artwork;
    @BindView(R.id.iv_track_url) ImageView iv_track_url;
    @BindView(R.id.iv_collection_url) ImageView iv_collection_url;
    @BindView(R.id.tv_detail_track_name) TextView tv_detail_track_name;
    @BindView(R.id.tv_detail_artist_name) TextView tv_detail_artist_name;
    @BindView(R.id.tv_detail_track_name1) TextView tv_detail_track_name1;
    @BindView(R.id.tv_detail_collection_name) TextView tv_detail_collection_name;
    @BindView(R.id.tv_release_date) TextView tv_release_date;
    @BindView(R.id.ll_track_view_url) LinearLayout ll_track_view_url;
    @BindView(R.id.ll_collection_view_url) LinearLayout ll_collection_view_url;
    @BindView(R.id.fab_favorite) FloatingActionButton fab_favorite;
    private Song song;
    private SQLiteDatabase mDb;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        song = getArguments().getParcelable("trackDetails");
        ButterKnife.bind(this, view);
        initViews();
        FavoritesDbHandler favDbHelper = new FavoritesDbHandler(getContext());
        mDb = favDbHelper.getWritableDatabase();
        return view;
    }

    private void initViews(){
        Picasso.with(getContext()).load(song.getArtworkUrl100()).into(iv_detail_artwork);

        //Since we only require onBitmapLoaded(), using Abstract Class instead of Interface
        MyBasePicassoTargetUtil target = new MyBasePicassoTargetUtil() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                iv_track_url.setImageBitmap(Util.fastblur(bitmap, 1f, 10));
                iv_collection_url.setImageBitmap(Util.fastblur(bitmap, 1f, 10));
            }
        };

        iv_track_url.setTag(target);
        iv_collection_url.setTag(target);
        Picasso.with(getContext()).load(song.getArtworkUrl100()).into(target);


        tv_detail_artist_name.setText(song.getArtistName());
        tv_detail_track_name.setText(song.getTrackName());
        tv_release_date.setText(Util.dateFormat(song.getReleaseDate()));
        tv_detail_collection_name.setText(song.getCollectionName()+" - $"+song.getCollectionPrice());
        tv_detail_track_name1.setText(song.getTrackName()+" - $"+song.getTrackPrice());

        ll_collection_view_url.setOnClickListener(this);
        ll_track_view_url.setOnClickListener(this);
        fab_favorite.setOnClickListener(this);

        if(isSongFavorite()){
            fab_favorite.setSelected(true);
        } else{
            fab_favorite.setSelected(false);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.ll_track_view_url:
                Intent trackViewIntent = new Intent(Intent.ACTION_VIEW);
                trackViewIntent.setData(Uri.parse(song.getTrackViewUrl()));
                startActivity(trackViewIntent);
                break;
            case R.id.ll_collection_view_url:
                Intent collectionViewIntent = new Intent(Intent.ACTION_VIEW);
                collectionViewIntent.setData(Uri.parse(song.getCollectionViewUrl()));
                startActivity(collectionViewIntent);
                break;
            case R.id.fab_favorite:
                if(!fab_favorite.isSelected()) {
                    fab_favorite.setSelected(true);
                    addFavoriteSong();
                } else {
                    fab_favorite.setSelected(false);
                    removeFavoriteSong();
                }
        }
    }

    private void addFavoriteSong(){
        ContentValues cv = new ContentValues();
        cv.put(FavoritesContract.FavoriteEntry.COLUMN_ARTIST_NAME, song.getArtistName());
        cv.put(FavoritesContract.FavoriteEntry.COLUMN_ARTWORK_URL, song.getArtworkUrl100());
        cv.put(FavoritesContract.FavoriteEntry.COLUMN_COLLECTION_NAME, song.getCollectionName());
        cv.put(FavoritesContract.FavoriteEntry.COLUMN_COLLECTION_PRICE, song.getCollectionPrice());
        cv.put(FavoritesContract.FavoriteEntry.COLUMN_COLLECTION_VIEW_URL, song.getCollectionViewUrl());
        cv.put(FavoritesContract.FavoriteEntry.COLUMN_GENRE_NAME, song.getPrimaryGenreName());
        cv.put(FavoritesContract.FavoriteEntry.COLUMN_PREVIEW_URL, song.getPreviewUrl());
        cv.put(FavoritesContract.FavoriteEntry.COLUMN_RELEASE_DATE, song.getReleaseDate());
        cv.put(FavoritesContract.FavoriteEntry.COLUMN_TRACK_NAME, song.getTrackName());
        cv.put(FavoritesContract.FavoriteEntry.COLUMN_TRACK_PRICE, song.getTrackPrice());
        cv.put(FavoritesContract.FavoriteEntry.COLUMN_TRACK_TIME, song.getTrackTimeMillis());
        cv.put(FavoritesContract.FavoriteEntry.COLUMN_TRACK_VIEW_URL, song.getTrackViewUrl());
        cv.put(FavoritesContract.FavoriteEntry.COLUMN_TRACK_ID, song.getTrackId());
        mDb.insert(FavoritesContract.FavoriteEntry.TABLE_NAME, null, cv);
        Toast.makeText(getContext().getApplicationContext(), "Added to your Favorites", Toast.LENGTH_SHORT).show();
    }
    private void removeFavoriteSong() {
        mDb.delete(FavoritesContract.FavoriteEntry.TABLE_NAME, FavoritesContract.FavoriteEntry.COLUMN_TRACK_ID+ "="
                + song.getTrackId(), null);
        Toast.makeText(getContext().getApplicationContext(), "Removed from Favorites", Toast.LENGTH_SHORT).show();
    }

    private boolean isSongFavorite(){
        FavoritesDbHandler favDbHandlerReadable = new FavoritesDbHandler(getContext());
        SQLiteDatabase mDbReadable = favDbHandlerReadable.getReadableDatabase();

        String sql = "SELECT * FROM "+FavoritesContract.FavoriteEntry.TABLE_NAME+ " WHERE "
                + FavoritesContract.FavoriteEntry.COLUMN_TRACK_ID + " = "+song.getTrackId();
        Cursor cursor = mDbReadable.rawQuery(sql, null);
        if(BuildConfig.DEBUG){
            Log.d(TAG, "getSongDetails: Cursor Count "+cursor.getCount());
        }

        return cursor.getCount()>0;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mDb!=null) {
            mDb.close();
            mDb = null;
        }
    }
}
