package com.hussainmukadam.droidtunes.detailspage.ui;

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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by hussain on 6/11/17.
 */

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";
    ImageView iv_detail_artwork;
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
        tv_detail_artist_name = (TextView) findViewById(R.id.tv_detail_artist_name);
        tv_detail_track_name = (TextView) findViewById(R.id.tv_detail_track_name);

        Picasso.with(this).load(song.getArtworkUrl100()).into(iv_detail_artwork);
        tv_detail_artist_name.setText(song.getArtistName());
        tv_detail_track_name.setText(song.getTrackName());
    }
}
