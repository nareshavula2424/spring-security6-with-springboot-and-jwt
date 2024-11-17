package com.naresh.spring_security.controller;

import com.naresh.spring_security.model.Users;
import com.naresh.spring_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    public UserService service;

//    @Autowired
//    public PasswordEncoder passwordEncoder;
   // public BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    @PostMapping("/register")
    public Users registerUser(@RequestBody  Users user){
         //user.setPassword(encoder.encode(user.getPassword()));
        //System.out.println(passwordEncoder.encode(user.getPassword()));
      return service.register(user);
    }
    @PostMapping("/login")
    public String login(@RequestBody Users user){
        return service.verify(user);

    }


}
