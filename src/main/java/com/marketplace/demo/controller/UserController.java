package com.marketplace.demo.controller;

import com.marketplace.demo.model.User;
import com.marketplace.demo.model.loginRequest.LoginRequest;
import com.marketplace.demo.repository.UserRepository;
import com.marketplace.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth/users")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //http://localhost:9092/auth/users/register
    @PostMapping("/register")
    public User createUser(@RequestBody User userObject){
        System.out.println("calling createUser");
        return userService.createUser(userObject);
    }

    //http://localhost:9092/auth/users/login
    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody LoginRequest loginRequest){
        System.out.println("calling loginUser =====>");
        return userService.loginUser(loginRequest);
    }

    //http://localhost:9092/auth/users/reset
    @PutMapping("/reset")
    public String updatePassword(@RequestBody String newPassword){
        System.out.println("calling updatePassword ==>");
        return userService.updatePassword(newPassword);
    }

    //http://localhost:9092/auth/users/email
    @PutMapping("/email")
    public String updateEmail(@RequestBody String newEmail){
        System.out.println("calling updateEmail ==>");
        return userService.updateEmail(newEmail);
    }

}
