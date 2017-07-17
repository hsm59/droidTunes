package com.hussainmukadam.droidtunes.mainpage.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hussainmukadam.droidtunes.R;
import com.hussainmukadam.droidtunes.detailpage.ui.DetailActivity;
import com.hussainmukadam.droidtunes.mainpage.models.Song;
import com.hussainmukadam.droidtunes.utils.Util;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hussain on 6/10/17.
 */

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    private List<Song> songsList;

    public SongAdapter(List<Song> songsList){
        this.songsList = songsList;
    }

    @Override
    public SongAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Song song = songsList.get(position);
        holder.tv_track_name.setText(song.getTrackName());
        holder.tv_artist_name.setText(song.getArtistName());
        holder.tv_genre_name.setText(song.getPrimaryGenreName());
        holder.tv_track_time.setText(Util.millis2String(Long.parseLong(song.getTrackTimeMillis())));
        Picasso.with(holder.iv_artwork100.getContext()).load(song.getArtworkUrl100()).into(holder.iv_artwork100);
        holder.rl_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.rl_layout.getContext(), DetailActivity.class);
                intent.putExtra("trackDetails", songsList.get(position));
                holder.rl_layout.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return songsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.rl_layout) RelativeLayout rl_layout;
        @BindView(R.id.tv_track_name) TextView tv_track_name;
        @BindView(R.id.tv_artist_name) TextView tv_artist_name;
        @BindView(R.id.tv_genre_name) TextView tv_genre_name;
        @BindView(R.id.tv_track_time) TextView tv_track_time;
        @BindView(R.id.iv_artwork100) ImageView iv_artwork100;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
