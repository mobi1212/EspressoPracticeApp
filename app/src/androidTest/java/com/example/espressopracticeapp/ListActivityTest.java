package com.example.espressopracticeapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ListActivityTest {

    private ActivityScenario<ListActivity> scenario;

    @Before
    public void setUp() {
        scenario = ActivityScenario.launch(ListActivity.class); // 啟動 ListActivity
    }

    @After
    public void cleanup() {
        scenario.close(); // 測試結束後關閉 Activity
    }

    // ✅ 畫面元件驗證
    @Test
    public void test_uiElements_areVisible() {
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_back)).check(matches(isDisplayed()));
    }

    // ✅ 滾動到「使用者 20」
    @Test
    public void test_scrollToItem_20() {
        onView(withId(R.id.recycler_view))
                .perform(scrollToPosition(19)); // 使用者 20 是位置 19
    }

    // ✅ 點擊「使用者 1」
    @Test
    public void test_clickUser1_navigateToDetail() {
        String expectedName = "使用者 1";
        onView(withId(R.id.recycler_view))
                .perform(actionOnItemAtPosition(0, click()));

        onView(withId(R.id.text_detail))
                .check(matches(withText("你選擇的是：" + expectedName)));
    }

    // ✅ 點擊「使用者 20」
    @Test
    public void test_clickUser20_navigateToDetail() {
        int position = 19;
        String expectedName = "使用者 " + (position + 1);

        onView(withId(R.id.recycler_view))
                .perform(scrollToPosition(position))
                .perform(actionOnItemAtPosition(position, click()));

        onView(withId(R.id.text_detail))
                .check(matches(withText("你選擇的是：" + expectedName)));
    }

    // ✅ 點擊任一項 → 進入 DetailActivity → 點返回 → 回 ListActivity
    @Test
    public void test_navigateToDetailAndBack() {
        int position = 2;
        onView(withId(R.id.recycler_view))
                .perform(actionOnItemAtPosition(position, click()));


        onView(withId(R.id.btn_back)).perform(click()); // 點返回按鈕
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed())); // 確認回到 ListActivity
    }
}
