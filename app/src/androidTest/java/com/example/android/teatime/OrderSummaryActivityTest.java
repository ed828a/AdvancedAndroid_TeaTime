package com.example.android.teatime;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.app.Instrumentation.ActivityResult;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;


/**
 * Created by Android on 11/25/2017.
 */
@RunWith(AndroidJUnit4.class)
public class OrderSummaryActivityTest {
    private static final Uri INTENT_DATA_MAILTO = Uri.parse("mailto:");

    private static String ORDER_SUMMARY_EMAIL_SUBJECT = "Freshly Brewed from TeaTime";
    private static String EMAIL_MESSAGE =
            "I just ordered a delicious tea from TeaTime. " +
                    "Next time you are craving a tea, check them out!";



    @Rule
    public IntentsTestRule<OrderSummaryActivity> mActivityRule = new IntentsTestRule<>(
            OrderSummaryActivity.class);

    @Before
    public void stubAllExternalIntents() {
        // By default Espresso Intents does not stub any Intents. Stubbing needs to be setup before
        // every test run. In this case all external Intents will be blocked.
        intending(not(isInternal())).respondWith(
                new ActivityResult(Activity.RESULT_OK, null));
    }

    @Test
    public void clickSendEmailButton_VerifyIntent() {

        // Types a phone number into the dialer edit text field and presses the call button.
        onView(withId(R.id.send_email_button)).perform(click());

        // Verify that an intent to the email app was sent with the correct action, phone
        // number and package. Think of Intents intended API as the equivalent to Mockito's verify.
        intended(allOf(
                hasAction(Intent.ACTION_SENDTO),
                hasData(INTENT_DATA_MAILTO),
                hasExtra(Intent.EXTRA_SUBJECT, ORDER_SUMMARY_EMAIL_SUBJECT),
                hasExtra(Intent.EXTRA_TEXT, EMAIL_MESSAGE)));
    }
}
