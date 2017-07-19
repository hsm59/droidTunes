package com.hussainmukadam.droidtunes.favoritepage;

import android.database.sqlite.SQLiteDatabase;

import com.hussainmukadam.droidtunes.BasePresenter;
import com.hussainmukadam.droidtunes.BaseView;
import com.hussainmukadam.droidtunes.mainpage.model.Song;

import java.util.List;

/**
 * Created by hussain on 7/19/17.
 */

public interface FavoritesMVPContract {
    interface View extends BaseView<Presenter>{

        void showProgress(boolean b);

        void displayList(List<Song> songsList);

        void showError(String errorMessage);
    }

    interface Presenter extends BasePresenter{

        void fetchDataFromDatabase(SQLiteDatabase mDb);

    }
}
