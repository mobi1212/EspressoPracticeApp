// ✅ LoadActivityTest.java（使用 CountingIdlingResource）
package com.example.espressopracticeapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.idling.CountingIdlingResource;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LoadActivityTest {

    private ActivityScenario<LoadActivity> scenario;
    private static final CountingIdlingResource idlingResource = new CountingIdlingResource("load_tasks");

    @Before
    public void setUp() {
        scenario = ActivityScenario.launch(LoadActivity.class);
        IdlingRegistry.getInstance().register(idlingResource);

        scenario.onActivity(activity -> {
            activity.setIdlingResource(idlingResource);
        });
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(idlingResource);
        scenario.close();
    }

    @Test
    public void test_loadActivityDisplaysDataAfterDelay() {
        onView(withId(R.id.btn_start_load)).perform(click());

        // 驗證文字與進度狀態
        onView(withId(R.id.text_user_name))
                .check(matches(withText("使用者名稱：阿偉")));

        onView(withId(R.id.text_credit_score))
                .check(matches(withText("信用評分：900")));

        onView(withId(R.id.progress_bar))
                .check(matches(notVisible()));
    }

    // Custom matcher: View 不可見
    private static TypeSafeMatcher<View> notVisible() {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                return item.getVisibility() != View.VISIBLE;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("View is not visible");
            }
        };
    }
}
