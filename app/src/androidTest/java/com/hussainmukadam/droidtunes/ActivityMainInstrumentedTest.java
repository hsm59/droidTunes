package com.hussainmukadam.droidtunes;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.hussainmukadam.droidtunes.mainpage.view.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by akshay on 13/7/17.
 */
@RunWith(AndroidJUnit4.class)
public class ActivityMainInstrumentedTest {

    @Rule
  public   ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public  void  verifyHints(){
        onView(withId(R.id.et_search)).check(matches(withHint(R.string.search_artists)));
    }
}
