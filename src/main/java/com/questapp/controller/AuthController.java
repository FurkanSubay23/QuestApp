package com.questapp.controller;

import com.questapp.entity.User;
import com.questapp.request.LoginRequest;
import com.questapp.security.JwtTokenManager;
import com.questapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtTokenManager tokenManager;
    private UserService userService;
    private PasswordEncoder encoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenManager tokenManager, UserService userService, PasswordEncoder encoder) {
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
        this.userService = userService;
        this.encoder = encoder;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());
        Authentication authentication = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = this.tokenManager.generateTokenManager(authentication);
        return new ResponseEntity<>("Bearer " + jwt, HttpStatus.OK);  // Burada tokenımızı döndürüyoruz.

    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody LoginRequest loginRequest) {
        User user = this.userService.getOneUserByUserName(loginRequest.getUserName());
        if (user != null)
            return new ResponseEntity<>("Username already in use", HttpStatus.BAD_REQUEST);
        User user1 = new User();
        user1.setUserName(loginRequest.getUserName());
        user1.setPassword(this.encoder.encode(loginRequest.getPassword()));
        userService.createUser(user1);
        return new ResponseEntity<>("User successfully registered", HttpStatus.CREATED);
    }

}
