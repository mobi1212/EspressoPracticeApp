package com.example.espressopracticeapp;

import java.util.List;

public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<String> loadUsers() {
        return repository.getAllUsers();
    }
}
