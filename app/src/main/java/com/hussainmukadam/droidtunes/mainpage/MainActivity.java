package com.hussainmukadam.droidtunes.mainpage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.hussainmukadam.droidtunes.R;

public class MainActivity extends AppCompatActivity implements TextView.OnEditorActionListener{
    EditText et_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_search = (EditText) findViewById(R.id.et_search);
        et_search.setOnEditorActionListener(this);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(actionId == EditorInfo.IME_ACTION_SEARCH){

            return true;
        }
        return false;
    }


}
