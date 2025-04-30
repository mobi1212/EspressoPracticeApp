package com.example.espressopracticeapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
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
public class CalculatorActivityTest {

    private ActivityScenario<CalculatorActivity> scenario;

    @Before
    public void setUp() {
        scenario = ActivityScenario.launch(CalculatorActivity.class);
    }

    @After
    public void tearDown() {
        if (scenario != null) scenario.close();
    }

    @Test
    public void test_uiElements_areVisible() {
        onView(withId(R.id.edit_num1)).check(matches(isDisplayed()));
        onView(withId(R.id.edit_num2)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_add)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_subtract)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_multiply)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_divide)).check(matches(isDisplayed()));
        onView(withId(R.id.text_result)).check(matches(isDisplayed()));
    }

    @Test
    public void test_addition() {
        input("4", "6");
        onView(withId(R.id.btn_add)).perform(click());
        onView(withId(R.id.text_result)).check(matches(withText("結果：10.0")));
    }

    @Test
    public void test_subtraction() {
        input("10", "3");
        onView(withId(R.id.btn_subtract)).perform(click());
        onView(withId(R.id.text_result)).check(matches(withText("結果：7.0")));
    }

    @Test
    public void test_multiplication() {
        input("7", "8");
        onView(withId(R.id.btn_multiply)).perform(click());
        onView(withId(R.id.text_result)).check(matches(withText("結果：56.0")));
    }

    @Test
    public void test_division() {
        input("10", "2");
        onView(withId(R.id.btn_divide)).perform(click());
        onView(withId(R.id.text_result)).check(matches(withText("結果：5.0")));
    }

    @Test
    public void test_divideByZero() {
        input("10", "0");
        onView(withId(R.id.btn_divide)).perform(click());
        onView(withId(R.id.text_result)).check(matches(withText("除數不能為 0")));
    }


    @Test
    public void test_emptyInput() {
        onView(withId(R.id.edit_num1)).perform(clearText());
        onView(withId(R.id.edit_num2)).perform(clearText());
        onView(withId(R.id.btn_add)).perform(click());
        onView(withId(R.id.text_result)).check(matches(withText("請輸入兩個數字")));
    }

    private void input(String num1, String num2) {
        onView(withId(R.id.edit_num1)).perform(clearText(), typeText(num1), closeSoftKeyboard());
        onView(withId(R.id.edit_num2)).perform(clearText(), typeText(num2), closeSoftKeyboard());
    }
}
