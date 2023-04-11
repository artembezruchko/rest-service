package com.example.restservice.controllers;

import com.example.restservice.dto.UserDto;
import com.example.restservice.entity.User;
import com.example.restservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);

    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

}
