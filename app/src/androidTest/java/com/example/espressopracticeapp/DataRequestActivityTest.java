package com.example.espressopracticeapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.allOf;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.intent.Intents;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DataRequestActivityTest {

    private Intent resultIntent;

    @Rule
    public ActivityScenarioRule<DataRequestActivity> activityRule =
            new ActivityScenarioRule<>(DataRequestActivity.class);

    @Before
    public void setUp() {
        Intents.init();
        resultIntent = new Intent();
        resultIntent.putExtra("user_name", "測試用戶");
        resultIntent.putExtra("credit_score", 888);
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void testLaunchWebIntent_andUpdateTextView() {
        intending(hasComponent(LoadActivity.class.getName()))
                .respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, resultIntent));

        onView(withId(R.id.btn_open_web)).perform(click());
        onView(withId(R.id.text_result)).check(matches(withText(containsString("測試用戶"))));
    }
}

