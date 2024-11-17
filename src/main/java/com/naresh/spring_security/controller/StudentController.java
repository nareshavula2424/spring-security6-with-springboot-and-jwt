package com.naresh.spring_security.controller;

import com.naresh.spring_security.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
public class StudentController {

    List<Student> listOfStudents = new ArrayList<>(List.of(
            new Student(1,"Naresh",20),
            new Student(2,"Nagendra",30)
    ));
    @GetMapping("/students")
    public List<Student> getStudents(){
        return listOfStudents;
    }
    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }
    @PostMapping("/students")
    public Student createStudent(@RequestBody  Student student){
        listOfStudents.add(student);
        return student;
    }
}
