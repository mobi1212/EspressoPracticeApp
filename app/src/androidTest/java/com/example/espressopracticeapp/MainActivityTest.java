package com.example.espressopracticeapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<com.example.espressopracticeapp.MainActivity> activityRule =
            new ActivityScenarioRule<>(com.example.espressopracticeapp.MainActivity.class);

    @Before
    public void setUp() {
        Intents.init();  // 初始化 Espresso Intents
    }

    @After
    public void tearDown() {
        Intents.release();  // 結束後釋放資源
    }

    // === 頁面跳轉測試 ===
    @Test
    public void testGoToLoginActivity() {
        onView(withId(R.id.btn_login)).perform(click());
        intended(hasComponent(com.example.espressopracticeapp.LoginActivity.class.getName()));
    }

    @Test
    public void testGoToListActivity() {
        onView(withId(R.id.btn_list)).perform(click());
        intended(hasComponent(com.example.espressopracticeapp.ListActivity.class.getName()));
    }

    @Test
    public void testGoToCalculatorActivity() {
        onView(withId(R.id.btn_calculator)).perform(click());
        intended(hasComponent(CalculatorActivity.class.getName()));
    }

    @Test
    public void testGoToLoadActivity() {
        onView(withId(R.id.btn_load)).perform(click());
        intended(hasComponent(LoadActivity.class.getName()));
    }

    @Test
    public void testGoToWebIntentActivity() {
        onView(withId(R.id.btn_WebIntent)).perform(click());
        intended(hasComponent(WebPageActivity.class.getName()));
    }

    // === 返回按鈕測試 ===
    @Test
    public void testLoginActivityBackToMain() {
        testBackNavigation(R.id.btn_login, R.id.btn_list);
    }

    @Test
    public void testListActivityBackToMain() {
        testBackNavigation(R.id.btn_list, R.id.btn_calculator);
    }

    @Test
    public void testCalculatorActivityBackToMain() {
        testBackNavigation(R.id.btn_calculator, R.id.btn_login);
    }

    @Test
    public void testLoadActivityBackToMain() {
        testBackNavigation(R.id.btn_load, R.id.btn_login); // 回來時確認主畫面的登入按鈕存在
    }

    @Test
    public void testWebIntentActivityBackToMain() {
        testBackNavigation(R.id.btn_WebIntent, R.id.btn_login); // 回來時確認主畫面的登入按鈕存在
    }

    @Test
    public void testBackgroundColor() {
        onView(withId(R.id.root_layout))
                .check(matches(CustomMatchers.withBackgroundColor("#A000BCD4")));
    }

    // === 共用返回測試 Helper Function ===
    private void testBackNavigation(int enterButtonId, int expectedMainButtonId) {
        onView(withId(enterButtonId)).perform(click());           // 點主畫面上的按鈕進入子頁面
        onView(withId(R.id.btn_back)).perform(click());           // 點子頁面的返回按鈕
        onView(withId(expectedMainButtonId)).check(matches(isDisplayed())); // 確認回主畫面
    }
}
