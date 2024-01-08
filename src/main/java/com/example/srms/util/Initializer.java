package com.example.srms.util;

import com.example.srms.Model.Student;
import com.example.srms.Repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

@Slf4j
@Component
public class Initializer implements ApplicationRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        log.info("========== SENDING DATA =========");


        Optional<Student> studentOptional = studentRepository.findStudentByEmail("admin@srms.go.tz");
        Student studentAccount;

        if(studentOptional.isPresent()){
            studentAccount = studentOptional.get();
        }
        else {
            studentAccount =  new Student();

            studentAccount.setFirstName("Super");
            studentAccount.setLastName("Admin");
            studentAccount.setPhone("255744210000");
            studentAccount.setEmail("admin@srms.go.tz");
            studentAccount.setGender("male");
            studentAccount.setDob(LocalDate.of(2000, Month.JANUARY, 1));
            studentAccount.setPassword(passwordEncoder.encode("12345678"));


        }
        studentRepository.save(studentAccount);

    }
}
