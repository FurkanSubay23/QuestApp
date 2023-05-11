package com.questapp.controller;

import com.questapp.entity.User;
import com.questapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userRepository) {
        this.userService = userRepository;
    }

    @GetMapping                                                             // Get All Users
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userID}")                                                // Get user by ID
    public User getOneUser(@PathVariable Long userID) {
        return userService.getOneUserById(userID);
    }

    @PostMapping                                                             // Save the user to user database
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{userID}")
    public User updateOneUser(@PathVariable Long userID, @RequestBody User newUser) {
        return userService.updateOneUser(userID,newUser);
    }

    @DeleteMapping("/{userID}")
    public void deleteOneUser(@PathVariable Long userID) {
        userService.deleteOneUser(userID);
    }


}