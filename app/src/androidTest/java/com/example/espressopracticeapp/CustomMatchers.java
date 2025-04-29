package com.example.espressopracticeapp;

import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import androidx.test.espresso.matcher.BoundedMatcher;
import org.hamcrest.Matcher;

public class CustomMatchers {
    private static final String TAG = "CustomMatchers"; // 用來標註 Log 顯示的標籤
    public static Matcher<View> withBackgroundColor(final String color) {
        return new BoundedMatcher<View, View>(View.class) {

            @Override
            protected boolean matchesSafely(View view) {
                // 取得背景顏色並轉換為顏色值
                if (view.getBackground() instanceof ColorDrawable) {
                    int backgroundColor = ((ColorDrawable) view.getBackground()).getColor();
                    // 轉換為十六進制顏色，並保留透明度（Alpha）
                    String backgroundColorHex = String.format("#%08X", backgroundColor); // 包含透明度（8位）
                    Log.d(TAG, "Actual background color with alpha: " + backgroundColorHex); // 除錯輸出實際顏色

                    // 比較顏色值（包括透明度）
                    boolean isMatch = backgroundColorHex.equalsIgnoreCase(color);
                    if (!isMatch) {
                        Log.d(TAG, "Expected color: " + color); // 如果顏色不匹配，輸出預期顏色
                    }
                    return isMatch;
                }
                Log.d(TAG, "Background is not a ColorDrawable");
                return false;
            }//檢查背景顏色

            @Override
            public void describeTo(org.hamcrest.Description description) {
                description.appendText("with background color: " + color);
            }
        };
    }
}
