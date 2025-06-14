package com.example.espressopracticeapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.espressopracticeapp.data.FakeUserRepository;
import com.example.espressopracticeapp.data.UserRepository;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    @Test
    public void testRecyclerViewWithStubRepository() {
        // 1. 使用 Mockito 創建 stub
        UserRepository stubRepo = mock(UserRepository.class);
        when(stubRepo.getAllUsers()).thenReturn(Arrays.asList("Alice", "Bob","Charlie"));

        // 2. 啟動 Activity 並注入 stub
        ListActivity.injectedRepository = stubRepo;
        ActivityScenario.launch(ListActivity.class);

        // 3. 驗證畫面顯示了 stub 資料
        onView(withText("Alice")).check(matches(isDisplayed()));
        onView(withText("Bob")).check(matches(isDisplayed()));
        onView(withText("Charlie")).check(matches(isDisplayed()));
    }

    @Test
    public void testReverseButton_reversesUserList() {
        ActivityScenario<ListActivity> scenario = ActivityScenario.launch(ListActivity.class);

        scenario.onActivity(activity -> {
            FakeUserRepository fakeRepo = new FakeUserRepository();
            ListActivity.injectedRepository = fakeRepo;
            activity.recreate(); // 重啟 onCreate 以套用 repository
        });

        // 驗證初始順序
        onView(withId(R.id.recycler_view))
                .perform(scrollToPosition(0)) // 滾動到第 0 項
                .check(matches(atPosition(0, withText("fake用戶1號"))));

        // 點擊「反轉」按鈕
        onView(withId(R.id.btn_reverse)).perform(click());

        // 驗證已反轉：應該是 fake用戶1號 在畫面上
        onView(withId(R.id.recycler_view))
                .perform(scrollToPosition(0)) // 滾動到第 0 項
                .check(matches(atPosition(0, withText("fake用戶3號"))));
    }

    // 這段程式碼放在 ListActivityTest.java 的最下方
    public static Matcher<View> atPosition(int position, Matcher<View> itemMatcher) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has item at position " + position + ": ");
                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(RecyclerView recyclerView) {
                RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                if (viewHolder == null) {
                    return false;
                }
                return itemMatcher.matches(viewHolder.itemView);
            }
        };
    }

}
