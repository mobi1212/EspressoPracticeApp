package com.example.espressopracticeapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AppFlowTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setUp() {
        // 啟動在 @Rule 內完成
    }

    @After
    public void tearDown() {
        // Espresso 無需額外清理
    }

    @Test
    public void test_mainToDetailAndBackFlow() {
        // Step 1: Main → ListActivity
        onView(withId(R.id.btn_list)).perform(click());

        // Step 2: List → 點選「使用者 5」
        onView(withId(R.id.recycler_view))
                .perform(actionOnItemAtPosition(4, click())); // index = 4 → 使用者 5

        // Step 3: Detail → 檢查是否顯示正確文字
        onView(withId(R.id.text_detail))
                .check(matches(withText("你選擇的是：使用者 5")));

        // Step 4: 點返回 → 回到 ListActivity
        onView(withId(R.id.btn_back)).perform(click());
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()));

        // Step 5: 點返回 → 回到 MainActivity
        onView(withId(R.id.btn_back)).perform(click());
        onView(withId(R.id.btn_calculator)).check(matches(isDisplayed()));
    }
}
