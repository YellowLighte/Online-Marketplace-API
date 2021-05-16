package com.marketplace.demo.controller;

import com.marketplace.demo.model.User;
import com.marketplace.demo.repository.UserRepository;
import com.marketplace.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String loginUser() {
        System.out.println("user logging in");
        return "user should be logging in";
    }

}
