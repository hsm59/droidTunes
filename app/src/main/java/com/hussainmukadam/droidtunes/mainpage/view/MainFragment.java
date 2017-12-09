package com.hussainmukadam.droidtunes.mainpage.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hussainmukadam.droidtunes.R;
import com.hussainmukadam.droidtunes.mainpage.MainContract;
import com.hussainmukadam.droidtunes.mainpage.adapter.SongAdapter;
import com.hussainmukadam.droidtunes.mainpage.model.Song;
import com.hussainmukadam.droidtunes.mainpage.presenter.MainPresenter;
import com.hussainmukadam.droidtunes.utils.Util;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hussain on 7/29/17.
 */

public class MainFragment extends Fragment implements MainContract.View, TextView.OnEditorActionListener, SongAdapter.OnItemClickListener {
    private static final String TAG = "MainFragment";
    SongAdapter songAdapter;
    String mArtistName;
    LinearLayoutManager linearLayoutManager;
    private MainContract.Presenter mainPagePresenter;
    private MainPresenter mMainPresenter;
    private OnItemClickListenerMain onItemClickListenerMain;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.rv_songs)
    RecyclerView rvSongs;

    public interface OnItemClickListenerMain {
        void onItemClickMainFragment(Song song, int position);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        setupRecycler();
        etSearch.setOnEditorActionListener(this);
        mMainPresenter = new MainPresenter(this);
        return view;
    }

    private void setupRecycler() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvSongs.setLayoutManager(linearLayoutManager);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            if (etSearch.getText().toString().length() == 0) {
                Toast.makeText(getContext().getApplicationContext(), "Please Enter Artist Name", Toast.LENGTH_SHORT).show();
            } else if (!Util.isConnected(getContext())) {
                Toast.makeText(getContext().getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
            } else {
                mArtistName = etSearch.getText().toString().trim().replace(" ", "+");
                Log.d(TAG, "onEditorAction: mSongName " + mArtistName);
                mMainPresenter.fetchSongsListFromServer(mArtistName);
            }
            return true;
        }
        return false;
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.mainPagePresenter = presenter;
    }

    @Override
    public void showSongsList(List<Song> songsList) {
        Util.hideSoftInput(getActivity());
        songAdapter = new SongAdapter(songsList, this);
        rvSongs.setAdapter(songAdapter);
        songAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String errorMessage) {
        Util.hideSoftInput(getActivity());
        Toast.makeText(getContext().getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSongItemClicked(Song song, int position) {
        onItemClickListenerMain.onItemClickMainFragment(song, position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onItemClickListenerMain = (OnItemClickListenerMain) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }
}
