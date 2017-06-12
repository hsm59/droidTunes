package com.hussainmukadam.droidtunes.mainpage.ui;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.hussainmukadam.droidtunes.R;
import com.hussainmukadam.droidtunes.mainpage.adapters.SongAdapter;
import com.hussainmukadam.droidtunes.mainpage.models.Song;
import com.hussainmukadam.droidtunes.utils.Util;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TextView.OnEditorActionListener{
    private static final String TAG = "MainActivity";
    ArrayList<Song> mSongsList;
    ProgressDialog mProgressDialog;
    SongAdapter songAdapter;
    String mSongName;
    EditText et_search;
    RecyclerView rv_songs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_search = (EditText) findViewById(R.id.et_search);
        rv_songs = (RecyclerView) findViewById(R.id.rv_songs);

        setupProgressDialog();
        setupRecycler();
        et_search.setOnEditorActionListener(this);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(actionId == EditorInfo.IME_ACTION_SEARCH){
            if(et_search.getText().toString().length()==0) {
                Toast.makeText(this, "Please Enter Artist Name", Toast.LENGTH_SHORT).show();
            } else if(!Util.isConnected(this)){
                Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            } else {
                mSongName = et_search.getText().toString().trim().replace(" ", "+");
                Log.d(TAG, "onEditorAction: mSongName "+mSongName);
                mProgressDialog.show();
                performSearch(mSongName);
            }
            return true;
        }
        return false;
    }

    private void performSearch(String artistName){
        Util.hideSoftInput(MainActivity.this);
        Ion.with(getApplicationContext()).load(getString(R.string.itunes_url)+artistName).asJsonObject().withResponse()
        .setCallback(new FutureCallback<Response<JsonObject>>() {
            @Override
            public void onCompleted(Exception e, Response<JsonObject> result) {
                if(e!=null){
                    Toast.makeText(MainActivity.this, "Some error occurred", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(result!=null){
                    JsonObject jsonSongs = result.getResult().getAsJsonObject();

                    try{
                        JSONObject obj = new JSONObject(jsonSongs.toString());
                        JSONArray resultsArray = obj.getJSONArray("results");

                        mSongsList = new ArrayList<>();
                        for (int i = 0; i < resultsArray.length(); i++) {
                            Song song = new Song();
                            JSONObject songItem = (JSONObject) resultsArray.get(i);

                            song.setTrackName(songItem.getString("trackName"));
                            song.setArtistName(songItem.getString("artistName"));
                            song.setPrimaryGenreName(songItem.getString("primaryGenreName"));
                            song.setArtworkUrl30(songItem.getString("artworkUrl30"));
                            song.setArtworkUrl100(songItem.getString("artworkUrl100"));
                            if(songItem.has("trackPrice")) {
                                song.setTrackPrice(songItem.getString("trackPrice"));
                            }
                            if(songItem.has("collectionPrice")){
                                song.setCollectionPrice(songItem.getString("collectionPrice"));
                            }
                            song.setCollectionName(songItem.getString("collectionName"));
                            song.setCollectionViewUrl(songItem.getString("collectionViewUrl"));
                            song.setTrackViewUrl(songItem.getString("trackViewUrl"));
                            song.setTrackTimeMillis(songItem.getString("trackTimeMillis"));
                            song.setReleaseDate(songItem.getString("releaseDate"));
                            song.setPreviewUrl(songItem.getString("previewUrl"));

                            mSongsList.add(song);
                        }

                    } catch(JSONException jsonException){
                        jsonException.printStackTrace();
                    }
                    mProgressDialog.dismiss();
                    songAdapter = new SongAdapter(mSongsList);
                    rv_songs.setAdapter(songAdapter);
                    songAdapter.notifyDataSetChanged();
                } else {
                    Log.d(TAG, "onCompleted: Result is null");
                }
            }
        });
    }

    private void setupRecycler(){
        rv_songs.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_songs.setLayoutManager(linearLayoutManager);
    }

    private void setupProgressDialog(){
        mProgressDialog = new ProgressDialog(MainActivity.this, R.style.ProgressBarTheme);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
    }

}
