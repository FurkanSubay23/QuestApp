package com.questapp.service;

import com.questapp.entity.User;

import java.util.List;


public interface UserService {


    List<User> getAllUsers();

    User getOneUserById(Long userID);

    User createUser(User user);

    User updateOneUser(Long userID, User newUser);

    void deleteOneUser(Long userID);

    User getOneUserByUserName(String username);
}
