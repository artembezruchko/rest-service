package com.example.restservice.controllers;

import com.example.restservice.dto.UserDto;
import com.example.restservice.entity.User;
import com.example.restservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        User user = userService.createUser(userDto.getTestName(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getPhotosId(),
                userDto.getData(),
                userDto.isUsed());
        return ResponseEntity.status(HttpStatus.CREATED).body(user);

    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(userDto.getId(), userDto.getTestName(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getPhotosId(),
                userDto.getData(),
                userDto.isUsed()));

    }

    @GetMapping("/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        try {
            User user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (NoSuchElementException e) {
            String errorMessage = "Failed to get user with id " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new User().setError(errorMessage));
        }

    }

    @GetMapping("/testName/{testName}")
    public ResponseEntity<List<User>> getUserByTestsByName(@PathVariable("testName") String testName) {
        try {
            List<User> user = userService.getUsersByTestName(testName);
            return ResponseEntity.ok(user);
        } catch (NoSuchElementException e) {
            String errorMessage = "Failed to get user with testName " + testName;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Arrays.asList(new User().setError(errorMessage)));
        }
    }

    @GetMapping("/getSingleTest/{testName}")
    public ResponseEntity<User> getUserByTestName(@PathVariable("testName") String testName) {
        try {
            User user = userService.getUsersByTestName(testName).get(0);
            userService.deleteUser(user.getId());
            return ResponseEntity.ok(user);
        } catch (NoSuchElementException e) {
            String errorMessage = "Failed to get user with testName " + testName;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new User().setError(errorMessage));
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

}
