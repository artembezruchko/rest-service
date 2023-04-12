package com.example.restservice.service.impl;

import com.example.restservice.entity.User;
import com.example.restservice.repository.UserRepository;
import com.example.restservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return userRepository.findById(id).get();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getUsersByTestName(String name) {
        User user = new User().setTestName(name).setUsed(false);
        Example<User> example = Example.of(user);
        return userRepository.findAll(example);
    }

    public User mapUser(String testName, String email, String password, String photosId, String data, boolean used){
        return new User()
                .setTestName(testName)
                .setEmail(email)
                .setPhotosId(photosId)
                .setPassword(password)
                .setData(data)
                .setUsed(used);
    }
}
