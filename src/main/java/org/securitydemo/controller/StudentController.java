package org.securitydemo.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.securitydemo.DTO.StudentDTO;
import org.securitydemo.Repository.StudentRepo;
import org.securitydemo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentController {

    private StudentRepo studentRepo;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/home")
    String welcome(){
        return "Welcome to Spring Boot Student Controller";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody StudentDTO studentDTO) {
        try {
            Student student = modelMapper.map(studentDTO, Student.class);
            Student savedStudent = studentRepo.save(student);
            StudentDTO responseDTO = modelMapper.map(savedStudent, StudentDTO.class);
            return ResponseEntity.status(201).body(responseDTO);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while registering student: " + e.getMessage());
        }
    }
}
