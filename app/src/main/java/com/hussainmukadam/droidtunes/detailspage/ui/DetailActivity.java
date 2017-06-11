package com.hussainmukadam.droidtunes.detailspage.ui;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.hussainmukadam.droidtunes.R;
import com.hussainmukadam.droidtunes.mainpage.models.Song;
import com.hussainmukadam.droidtunes.utils.Util;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

/**
 * Created by hussain on 6/11/17.
 */

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";
    ImageView iv_detail_artwork, iv_track_url, iv_collection_url;
    TextView tv_detail_track_name, tv_detail_artist_name;
    Song song;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Track Details");
        setContentView(R.layout.activity_detail);
        song = getIntent().getParcelableExtra("trackDetails");
        initViews();
    }

    private void initViews(){
        iv_detail_artwork = (ImageView) findViewById(R.id.iv_detail_artwork);
        iv_track_url = (ImageView) findViewById(R.id.iv_track_url);
        iv_collection_url = (ImageView) findViewById(R.id.iv_collection_url);
        tv_detail_artist_name = (TextView) findViewById(R.id.tv_detail_artist_name);
        tv_detail_track_name = (TextView) findViewById(R.id.tv_detail_track_name);

        Picasso.with(this).load(song.getArtworkUrl100()).into(iv_detail_artwork);
        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                iv_track_url.setImageBitmap(Util.fastblur(bitmap, 1f, 10));
                iv_collection_url.setImageBitmap(Util.fastblur(bitmap, 1f, 10));
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        iv_track_url.setTag(target);
        iv_collection_url.setTag(target);
        Picasso.with(this).load(song.getArtworkUrl100()).into(target);

        tv_detail_artist_name.setText(song.getArtistName());
        tv_detail_track_name.setText(song.getTrackName());
    }
}
