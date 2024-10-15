package org.securitydemo.config;

import lombok.RequiredArgsConstructor;
import org.securitydemo.Repository.StudentRepo;
import org.securitydemo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentDetailService implements UserDetailsService {

    @Autowired
    StudentRepo studentRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

        List<GrantedAuthority> grantedAuthorities = List.of(new SimpleGrantedAuthority(student.getRoles()));
        return new User(student.getUsername(), student.getPassword(), grantedAuthorities);
    }
}

