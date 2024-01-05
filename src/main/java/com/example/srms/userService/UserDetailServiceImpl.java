package com.example.srms.userService;

import com.example.srms.Model.Student;
import com.example.srms.Repository.StudentRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private StudentRepository studentRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student studentAccount =  studentRepository.findStudentByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
//        ("User found with username {} ", studentAccount.getEmail());

        return UserDetailImpl.build(studentAccount);
    }
}
