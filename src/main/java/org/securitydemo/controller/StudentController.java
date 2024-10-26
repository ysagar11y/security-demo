package org.securitydemo.controller;


import lombok.RequiredArgsConstructor;
import org.securitydemo.Repository.StudentRepo;
import org.securitydemo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    StudentRepo studentRepo;


    @PostMapping("/register")
    public ResponseEntity<Student> register(@RequestBody Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        studentRepo.save(student);
        return ResponseEntity.ok(student);

    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Security Demo";
    }

}
