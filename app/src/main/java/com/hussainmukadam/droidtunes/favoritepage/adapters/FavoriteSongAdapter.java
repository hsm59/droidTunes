package com.hussainmukadam.droidtunes.favoritepage.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hussainmukadam.droidtunes.BuildConfig;
import com.hussainmukadam.droidtunes.R;
import com.hussainmukadam.droidtunes.detailpage.ui.DetailActivity;
import com.hussainmukadam.droidtunes.mainpage.adapters.SongAdapter;
import com.hussainmukadam.droidtunes.mainpage.models.Song;
import com.hussainmukadam.droidtunes.utils.Util;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hussain on 7/15/17.
 */

public class FavoriteSongAdapter extends RecyclerView.Adapter<FavoriteSongAdapter.ViewHolder>{
    private static final String TAG = "FavoriteSongAdapter";
    private List<Song> songsList;

    public FavoriteSongAdapter(List<Song> songsList){
        this.songsList = songsList;
    }

    @Override
    public FavoriteSongAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_favorite_item, parent, false);
        return new FavoriteSongAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FavoriteSongAdapter.ViewHolder holder, final int position) {
        Song song = songsList.get(position);

        if(BuildConfig.DEBUG){
            Log.d(TAG, "onBindViewHolder: SongsList in Favorites "+songsList.get(position).getArtworkUrl100());
        }

        Picasso.with(holder.iv_favorite_song_item.getContext()).load(song.getArtworkUrl100()).into(holder.iv_favorite_song_item);
//        holder.ll_favorite_song_item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(holder.ll_favorite_song_item.getContext(), DetailActivity.class);
//                intent.putExtra("trackDetails", songsList.get(position));
//                holder.ll_favorite_song_item.getContext().startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return songsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.favorite_songs_item)
        ImageView iv_favorite_song_item;
        @BindView(R.id.ll_favorite_song_item)
        LinearLayout ll_favorite_song_item;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
