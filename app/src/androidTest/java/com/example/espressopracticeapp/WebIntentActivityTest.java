package com.example.espressopracticeapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

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
public class WebIntentActivityTest {

    @Rule
    public ActivityScenarioRule<WebPageActivity> activityRule =
            new ActivityScenarioRule<>(WebPageActivity.class);

    @Before
    public void setUp() {
        Intents.init();

        // 模擬 ACTION_VIEW 的回傳結果
        Intent resultData = new Intent();  // 可加入額外資料 if needed
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(0, resultData);

        intending(allOf(
                hasAction(Intent.ACTION_VIEW),
                hasData("https://www.google.com.tw/")
        )).respondWith(result);
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void testLaunchWebIntent_andUpdateTextView() {
        onView(withId(R.id.btn_open_web)).perform(click());

        // 確認有發出正確的 Intent
        intended(allOf(
                hasAction(Intent.ACTION_VIEW),
                hasData("https://www.google.com.tw/")
        ));

        // 確認文字已更新
        onView(withId(R.id.text_result))
                .check(matches(withText("模擬已完成，網頁應已開啟")));
    }
}
