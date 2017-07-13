package com.hussainmukadam.droidtunes;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.hussainmukadam.droidtunes.mainpage.ui.MainActivity;

import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * Created by akshay on 13/7/17.
 */
@RunWith(AndroidJUnit4.class)
public class ActivityMainInstrumentedTest {

    @Rule
  public   ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
}
