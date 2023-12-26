package com.example.srms.config;

import com.example.srms.Model.Student;
import com.example.srms.Repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    CommandLineRunner commandLineRunner (StudentRepository repository) {
        return args -> {
            Student zedeck = new Student(
                    "Zedeck", "zedeck17@gmail.com", LocalDate.of(2000, Month.JUNE, 17)
            );

            Student cotton = new Student(
                    "Cotton", "cotton@gmail.com", LocalDate.of(2002,Month.AUGUST, 20)
            );

            repository.saveAll(
                    List.of(zedeck, cotton)
            );
        };
    }
}
