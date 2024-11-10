package org.securitydemo.Services;

import org.securitydemo.DTO.StudentDTO;
import org.securitydemo.entity.Student;

public interface StudentService {

    Student getStudentById(int id);
    Student registerStudent(StudentDTO studentDTO);
}
