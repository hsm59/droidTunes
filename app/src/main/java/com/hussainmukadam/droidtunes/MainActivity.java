package com.hussainmukadam.droidtunes;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import com.hussainmukadam.droidtunes.favoritepage.view.FavoriteFragment;
import com.hussainmukadam.droidtunes.mainpage.view.MainFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.host_container, new MainFragment())
                .commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_screen_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_favorite:
                switchContent(new FavoriteFragment(), null, new FavoriteFragment().getTag());
        }
        return super.onOptionsItemSelected(item);
    }

    public void switchContent(Fragment fragment, Bundle args, String tag) {

        fragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.host_container, fragment)
                .addToBackStack(tag).commit();
    }
}
