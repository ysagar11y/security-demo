package org.securitydemo.Services.impl;

import org.modelmapper.ModelMapper;
import org.securitydemo.DTO.StudentDTO;
import org.securitydemo.Repository.StudentRepo;
import org.securitydemo.Services.StudentService;
import org.securitydemo.entity.Student;
import org.securitydemo.exception.NoStudentFoundException;
import org.securitydemo.exception.StudentAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    StudentRepo studentRepo;

    public Optional<Student> getStudentByMail(String mail) throws NoStudentFoundException{
        if(studentRepo.findByEmail(mail).isEmpty()) { throw new NoStudentFoundException("No student is there with this mail ID");}
        Optional<Student> student=  studentRepo.findByEmail(mail);
        return student;
    }

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }


    @Override
    public Student registerStudent(StudentDTO studentDTO) throws StudentAlreadyExistException{
        if(studentRepo.findByEmail(studentDTO.getEmail()).isPresent()) throw new StudentAlreadyExistException("Student Already exist try with new Email ID");
        Student student = modelMapper.map(studentDTO, Student.class);
        return studentRepo.save(student);
    }

}
