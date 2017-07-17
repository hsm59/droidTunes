package com.hussainmukadam.droidtunes.mainpage;

import com.hussainmukadam.droidtunes.BasePresenter;
import com.hussainmukadam.droidtunes.BaseView;
import com.hussainmukadam.droidtunes.mainpage.model.Song;

import java.util.List;

/**
 * Created by hussain on 17/7/17.
 */

public interface MainContract {
    interface View extends BaseView<Presenter>{

        void showSongsList(List<Song> songsList);

        void showProgressBar();

        void hideProgressBar();

        void showError(String errorMessage);


    }


    interface Presenter extends BasePresenter{

        void fetchSongsListFromServer();

        void onSongSelected(int position);

    }
}
