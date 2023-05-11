package com.questapp.service;

import com.questapp.entity.User;
import com.questapp.exception.ErrorHandling;
import com.questapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getOneUserById(Long userID) {
        Optional<User> user = userRepository.findById(userID);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ErrorHandling("This is not acceptable ID " + userID);
        }
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateOneUser(Long userID, User newUser) {
        Optional<User> user = userRepository.findById(userID);
        if (user.isPresent()) {
            User foundUser = user.get();
            foundUser = newUser;
            foundUser.setId(userID);
            return userRepository.save(foundUser);
        } else {
            throw new ErrorHandling("This is not acceptable ID " + userID);
        }
    }

    @Override
    public void deleteOneUser(Long userID) {
        userRepository.deleteById(userID);
    }

    @Override
    public User getOneUserByUserName(String username) {
        User user1;
        try {
            Optional<User> user = Optional.ofNullable(userRepository.findByUserName(username));
            return user.orElse(null);
        } catch (Exception e) {
            throw new ErrorHandling("This is not acceptable userName !!! " + username);
        }
    }
}