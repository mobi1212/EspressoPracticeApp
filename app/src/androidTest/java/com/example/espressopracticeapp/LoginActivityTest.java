package com.example.espressopracticeapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    private ActivityScenario<LoginActivity> scenario;

    @Before
    public void setup() {
        scenario = ActivityScenario.launch(LoginActivity.class); // 啟動 LoginActivity
    }

    @After
    public void cleanup() {
        scenario.close(); // 測試結束後關閉 Activity
    }

    @Test
    public void test_uiElements_areVisible() {
        onView(withId(R.id.edit_username)).check(matches(isDisplayed()));
        onView(withId(R.id.edit_password)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_login)).check(matches(withText("登入")));
    }

    @Test
    public void test_loginSuccess() {
        onView(withId(R.id.edit_username)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.edit_password)).perform(typeText("1234"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());
        onView(withId(R.id.text_result)).check(matches(withText("登入成功")));
    }

    @Test
    public void test_loginFailure() {
        onView(withId(R.id.edit_username)).perform(clearText(), typeText("wrongUser"), closeSoftKeyboard());
        onView(withId(R.id.edit_password)).perform(clearText(), typeText("wrongPass"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());
        onView(withId(R.id.text_result)).check(matches(withText("帳號或密碼錯誤")));
    }

    @Test
    public void test_loginEmptyFields() {
        onView(withId(R.id.edit_username)).perform(clearText());
        onView(withId(R.id.edit_password)).perform(clearText());
        onView(withId(R.id.btn_login)).perform(click());
        onView(withId(R.id.text_result)).check(matches(withText("帳號或密碼錯誤")));
    }
}
