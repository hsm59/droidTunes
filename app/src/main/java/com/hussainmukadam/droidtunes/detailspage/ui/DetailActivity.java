package com.hussainmukadam.droidtunes.detailspage.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hussainmukadam.droidtunes.R;
import com.hussainmukadam.droidtunes.mainpage.models.Song;
import com.hussainmukadam.droidtunes.utils.Util;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hussain on 6/11/17.
 */

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "DetailActivity";
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
    Song song;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Track Details");
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        song = getIntent().getParcelableExtra("trackDetails");
        initViews();
    }

    private void initViews(){
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
        tv_release_date.setText(Util.dateFormat(song.getReleaseDate()));
        tv_detail_collection_name.setText(song.getCollectionName()+" - $"+song.getCollectionPrice());
        tv_detail_track_name1.setText(song.getTrackName()+" - $"+song.getTrackPrice());

        ll_collection_view_url.setOnClickListener(this);
        ll_track_view_url.setOnClickListener(this);
        fab_favorite.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.ll_track_view_url:
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(song.getTrackViewUrl()));
                startActivity(i);
                break;
            case R.id.ll_collection_view_url:
                Intent i1 = new Intent(Intent.ACTION_VIEW);
                i1.setData(Uri.parse(song.getCollectionViewUrl()));
                startActivity(i1);
                break;
            case R.id.fab_favorite:
                fab_favorite.setSelected(true);
//                Intent i2 = new Intent(Intent.ACTION_VIEW);
//                i2.setData(Uri.parse(song.getPreviewUrl()));
//                startActivity(i2);
        }
    }
}
