package com.questapp.service;


import com.questapp.entity.User;
import com.questapp.security.JwtUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {


    private UserService userService;

    @Autowired
    public UserDetailsServiceImp(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {   // bu method userdetails classındaki bilgiler ile giriş sorguluyor.
        User user = userService.getOneUserByUserName(username);
        return JwtUserDetail.createUser(user);
    }

    public UserDetails loadUserById(Long Id) {
        User user = userService.getOneUserById(Id);
        return JwtUserDetail.createUser(user);
    }
}
