package com.naresh.spring_security.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {
    @GetMapping("/")
    public String getGreetMsg(HttpServletRequest request){
        return "Welcome to Telusuko "+ request.getSession().getId();
    }
}
