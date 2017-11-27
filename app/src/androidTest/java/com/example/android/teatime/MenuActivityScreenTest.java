package com.example.android.teatime;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

/**
 * Created by Android on 11/24/2017.
 */
@RunWith(AndroidJUnit4.class)
public class MenuActivityScreenTest {
    @Rule
    public ActivityTestRule<MenuActivity> mMenuActivity =
            new ActivityTestRule<>(MenuActivity.class);

    @Test
    public void clickItemInGridView(){
        //1. find the view
        //2. perform action on the view
        onData(anything())                                                  // onData(ObjectMatcher)
                .inAdapterView(withId(R.id.tea_grid_view)).atPosition(0)    // .DataOptions
                .perform(click());                                          // .perform(ViewAction)

        //3. check if the view does what we expected
        onView(withId(R.id.tea_name_text_view)).check(matches(withText("Black Tea")));


    }

}