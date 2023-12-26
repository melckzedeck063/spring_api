package com.example.srms.Service;

import com.example.srms.Model.Student;
import netscape.javascript.JSObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class StudentService {

    @GetMapping
    public List<Student> getStudents (){
        return List.of(
                new Student(
                        1L, "Melckzedeck", "melckzedeck063@gmail.com", LocalDate.of(1999, Month.JUNE, 17), 24
                )
        );
    }
}
