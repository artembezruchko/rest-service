package com.example.restservice.service;

import com.example.restservice.entity.User;

import java.util.List;

public interface UserService {

    User createUser(String testName, String email, String password, String photosId, String data, boolean used);

    User updateUser(Long id, String testName, String email, String password, String photosId, String data, boolean used);

    User getUserById(Long id);

    void deleteUser(Long id);

    List<User> getUsersByTestName(String name);
}
