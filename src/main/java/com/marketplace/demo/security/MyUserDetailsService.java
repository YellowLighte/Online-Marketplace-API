package com.marketplace.demo.security;

import com.marketplace.demo.model.User;
import com.marketplace.demo.repository.UserRepository;
import com.marketplace.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserService userService;
//    private AdventureService adventureService;


    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

//    @Autowired
//    public void setAdventureService(AdventureService adventureService){
//        this.adventureService = adventureService;
//    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findUserByEmail(email);
        return new MyUserDetails(user);
    }
}
