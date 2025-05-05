package com.example.espressopracticeapp.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FakeUserRepository implements UserRepository {
    private final List<String> fakeData;
    public FakeUserRepository() {
        fakeData = new ArrayList<>();
        fakeData.add("fake用戶1號");
        fakeData.add("fake用戶2號");
        fakeData.add("fake用戶3號");
    }
    @Override
    public List<String> getAllUsers() {
        return new ArrayList<>(fakeData);
    }
    @Override
    public List<String> getUsersReversed() {
        List<String> reversed = new ArrayList<>(fakeData);
        Collections.reverse(reversed);
        return reversed;
    }
    @Override
    public List<String> resetToOriginalOrder() {
        return getAllUsers();
    }
}
