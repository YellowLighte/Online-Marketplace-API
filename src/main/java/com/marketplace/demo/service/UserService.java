package com.marketplace.demo.service;

import com.marketplace.demo.exception.InformationExistException;
import com.marketplace.demo.exception.InformationNotFoundException;
import com.marketplace.demo.model.User;
import com.marketplace.demo.model.loginRequest.LoginRequest;
import com.marketplace.demo.model.response.LoginResponse;
import com.marketplace.demo.repository.UserRepository;
import com.marketplace.demo.security.JWTUtils;
import com.marketplace.demo.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JWTUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    public User createUser(User userObject){
        System.out.println("service calling createUser =====>");

        if (!userRepository.existsByEmail(userObject.getEmail())){
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            return userRepository.save(userObject);
        } else {
            throw new InformationExistException("user with email address " + userObject.getEmail() + " already exists.");
        }
    }

    public ResponseEntity<Object> loginUser(LoginRequest loginRequest){
        System.out.println("service calling loginUser =====>");
        try{
            authenticationManager.authenticate(new
                    UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
            final String JWT = jwtUtils.generateToken(userDetails);
            return ResponseEntity.ok(new LoginResponse(JWT));

        }catch(NullPointerException e){
            throw new InformationNotFoundException("user with email address " + loginRequest.getEmail() + " not found");
        }
    }

    public String updatePassword(String newPassword){
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepository.findUserByEmail(myUserDetails.getUser().getEmail());
        if (currentUser != null){
            currentUser.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(currentUser);
            return "Password successfully updated.";
        }
        return "Error updating the password";
    }

    public String updateEmail(String newEmail){
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = myUserDetails.getUser();
        if (currentUser.getEmail().equals(newEmail)){
            throw new InformationExistException("The submitted email matches the current email address on file.");
        } else {
            currentUser.setEmail(newEmail);
            userRepository.save(currentUser);
            return "Email updated successfully";
        }
    }

}
