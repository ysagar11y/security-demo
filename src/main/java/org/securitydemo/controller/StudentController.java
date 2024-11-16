package org.securitydemo.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.securitydemo.DTO.StudentDTO;
import org.securitydemo.Repository.StudentRepo;
import org.securitydemo.Services.StudentService;
import org.securitydemo.entity.Student;
import org.securitydemo.exception.NoStudentFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StudentController {

    @Autowired
    StudentService studentService;


    @GetMapping("/getStudent/{email}")
    public ResponseEntity<Student> getStudent(@PathVariable String email) throws NoStudentFoundException {
        Optional<Student> student = studentService.getStudentByMail(email);
        return new ResponseEntity<>(student.orElseThrow(() -> new NoStudentFoundException("There is no Student with email: "+email)), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.status(HttpStatus.OK).body(students);
    }


    @PostMapping("/register")
    public ResponseEntity<StudentDTO> register(@Valid @RequestBody StudentDTO studentDTO) throws Exception{
        studentService.registerStudent(studentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentDTO);
    }

}
