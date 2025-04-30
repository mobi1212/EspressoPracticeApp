package com.example.espressopracticeapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {

    private ActivityScenario<DetailActivity> scenario;

    @After
    public void cleanup() {
        if (scenario != null) scenario.close();
    }

    @Test
    public void test_displayUsernameCorrectly() {
        Intent intent = new Intent();
        intent.putExtra("username", "使用者 5");


        scenario = ActivityScenario.launch(intent.setClassName(
                "com.example.espressopracticeapp",
                DetailActivity.class.getName()));

        onView(withId(R.id.text_detail))
                .check(matches(withText("你選擇的是：使用者 5")));
    }

    @Test
    public void test_displayErrorWhenUsernameMissing() {
        scenario = ActivityScenario.launch(DetailActivity.class);

        onView(withId(R.id.text_detail))
                .check(matches(withText("未收到使用者資料")));
    }

    @Test
    public void test_clickBackButton_returnToList() {
        Intent intent = new Intent();
        intent.putExtra("username", "使用者 3");

        scenario = ActivityScenario.launch(intent.setClassName(
                "com.example.espressopracticeapp",
                DetailActivity.class.getName()));

        onView(withId(R.id.btn_back)).perform(click());

        // 成功點返回 → Espresso 不會報錯就是通過，但可以驗證畫面元素消失或自動回 ListActivity
        // 若需要回到 ListActivity 再驗證畫面，建議搭配整合測試做
    }
}
