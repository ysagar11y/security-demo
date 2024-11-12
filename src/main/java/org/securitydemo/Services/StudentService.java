package org.securitydemo.Services;

import org.securitydemo.DTO.StudentDTO;
import org.securitydemo.entity.Student;
import org.securitydemo.exception.NoStudentFoundException;
import org.securitydemo.exception.StudentAlreadyExistException;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<Student> getAllStudents();
    Optional<Student> getStudentByMail(String mail) throws NoStudentFoundException;
    Student registerStudent(StudentDTO studentDTO) throws StudentAlreadyExistException;
}
