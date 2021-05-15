package com.marketplace.demo.controller;

import com.marketplace.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth/users")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //http://localhost:9092/auth/users/register
    @PostMapping("/register")
    public String createUser(){
        System.out.println("calling createUser");
        return "new customer should be created.";
        //return userService.createUser(userObject);
    }

    //http://localhost:9092/auth/users/login
    @PostMapping("/login")
    public String loginUser() {
        System.out.println("user logging in");
        return "user should be logging in";
    }
//    public ResponseEntity<Object> loginUser(@RequestBody LoginRequest loginRequest){
//        System.out.println("calling loginUser =====>");
//        return userService.loginUser(loginRequest);
//    }

}
