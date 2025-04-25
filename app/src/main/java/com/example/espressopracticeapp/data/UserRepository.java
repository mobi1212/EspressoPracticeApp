package com.example.espressopracticeapp.data;

import java.util.List;

public interface UserRepository {
    List<String> getAllUsers();
    List<String> getUsersReversed();
    List<String> resetToOriginalOrder();

}
