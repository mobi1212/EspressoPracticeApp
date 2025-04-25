package com.example.espressopracticeapp.service;

import com.example.espressopracticeapp.data.UserRepository;

import java.util.List;

public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<String> loadUsers() {
        return repository.getAllUsers();
    }

    public List<String> loadUsersReversed() {
        return repository.getUsersReversed();
    }

    public List<String> resetToOriginalOrder() {
        return repository.resetToOriginalOrder();
    }

}
