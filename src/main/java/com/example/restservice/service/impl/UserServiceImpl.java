package com.example.restservice.service.impl;

import com.example.restservice.entity.User;
import com.example.restservice.repository.UserRepository;
import com.example.restservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(String testName, String email, String password, String photosId, String data, boolean used) {
        User user = mapUser(testName, email, password, photosId, data, used);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, String testName, String email, String password, String photosId, String data, boolean used) {
        User user =  mapUser(testName, email, password, photosId, data, used);
        user.setId(id);
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        try {
            return userRepository.findById(id).get();
        } catch (Exception e) {
            return new User();
        }
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User mapUser(String testName, String email, String password, String photosId, String data, boolean used){
        User user = new User();
        user.setTestName(testName);
        user.setEmail(email);
        user.setPhotosId(photosId);
        user.setPassword(password);
        user.setData(data);
        user.setUsed(used);
        return user;
    }
}
