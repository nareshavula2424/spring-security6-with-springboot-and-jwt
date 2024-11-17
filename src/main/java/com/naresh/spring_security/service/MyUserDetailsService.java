package com.naresh.spring_security.service;

import com.naresh.spring_security.model.UserPrinciple;
import com.naresh.spring_security.model.Users;
import com.naresh.spring_security.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    public UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user  = userRepo.findByUsername(username);
        System.out.println("User " +user.getUsername());
        System.out.println("User " +user.getPassword());
        System.out.println("User " +user);

        if(user == null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("user not found");
        }
        return new UserPrinciple(user);
    }
}
