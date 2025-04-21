package com.example.espressopracticeapp;

import java.util.ArrayList;
import java.util.List;

public class RealUserRepository implements UserRepository {

    @Override
    public List<String> getAllUsers() {
        List<String> users = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            users.add("使用者 " + i);
        }
        return users;
    }
}
