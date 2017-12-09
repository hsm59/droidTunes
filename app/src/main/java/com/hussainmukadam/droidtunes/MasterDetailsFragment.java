package com.hussainmukadam.droidtunes;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hussainmukadam.droidtunes.detailpage.view.DetailFragment;
import com.hussainmukadam.droidtunes.mainpage.model.Song;
import com.hussainmukadam.droidtunes.mainpage.view.MainFragment;

/**
 * Created by hussain on 09/12/17.
 */

public class MasterDetailsFragment extends Fragment implements MainActivity.OnDetailsPaneChangeListener{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_master_detail, container, false);

        getChildFragmentManager().beginTransaction()
                .replace(R.id.main_container, new MainFragment())
                .commit();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MainActivity activity = (MainActivity) context;
        activity.setOnDetailsPaneChangeListener(this);
    }

    @Override
    public void onDetailFragmentChange(Song song, int position) {
        Fragment detailFragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("trackDetails", song);
        detailFragment.setArguments(args);
        getChildFragmentManager().beginTransaction()
                .replace(R.id.details_container, detailFragment)
                .commit();
    }
}
