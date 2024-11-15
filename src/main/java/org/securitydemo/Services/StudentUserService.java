package org.securitydemo.Services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.securitydemo.Repository.StudentRepo;
import org.securitydemo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class StudentUserService implements UserDetailsService {

    private StudentRepo studentRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepo.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("No user found with username: " + username));

        Set<GrantedAuthority> authorities = student.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(student.getEmail(), student.getPassword(), authorities);
    }
}
