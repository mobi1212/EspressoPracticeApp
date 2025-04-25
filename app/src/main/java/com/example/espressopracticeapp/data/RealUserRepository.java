package com.example.espressopracticeapp.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RealUserRepository implements UserRepository {

    // ✅ 原始資料只建立一次
    private final List<String> users;

    public RealUserRepository() {
        users = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            users.add("使用者 " + i);
        }
    }

    // ✅ 取得原始順序的資料（不變動原資料）
    @Override
    public List<String> getAllUsers() {
        return new ArrayList<>(users); // 回傳複製的，不影響原順序
    }

    // ✅ 取得反轉後的資料
    public List<String> getUsersReversed() {
        List<String> reversed = new ArrayList<>(users);
        Collections.reverse(reversed);
        return reversed;
    }

    // ✅ 回復成原始順序（其實就是再呼叫 getAllUsers）
    public List<String> resetToOriginalOrder() {
        return getAllUsers(); // 保持一致邏輯
    }
}
