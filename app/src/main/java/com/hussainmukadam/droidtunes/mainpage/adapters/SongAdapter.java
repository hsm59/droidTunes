package com.hussainmukadam.droidtunes.mainpage.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hussainmukadam.droidtunes.R;
import com.hussainmukadam.droidtunes.mainpage.models.Song;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by hussain on 6/10/17.
 */

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    private ArrayList<Song> songsList;

    public SongAdapter(ArrayList<Song> songsList){
        this.songsList = songsList;
    }

    @Override
    public SongAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Song song = songsList.get(position);
        holder.tv_track_name.setText(song.getTrackName());
        holder.tv_artist_name.setText(song.getArtistName());
        Picasso.with(holder.iv_artwork100.getContext()).load(song.getArtworkUrl100()).into(holder.iv_artwork100);
    }

    @Override
    public int getItemCount() {
        return 50;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_track_name;
        private ImageView iv_artwork100;
        private TextView tv_artist_name;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_track_name = (TextView) itemView.findViewById(R.id.tv_track_name);
            tv_artist_name = (TextView) itemView.findViewById(R.id.tv_artist_name);
            iv_artwork100 = (ImageView) itemView.findViewById(R.id.iv_artwork100);
        }
    }
}
