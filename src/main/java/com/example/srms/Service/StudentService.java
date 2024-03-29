package com.example.srms.Service;

import com.example.srms.Model.Student;
import com.example.srms.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public List<Student> getStudents (){
        return studentRepository.findAll();
    }

    @PostMapping
    public void addNewStudent (Student student) {

        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());

        if(studentByEmail.isPresent()){
            throw new IllegalStateException("Email already exists");
        }
        studentRepository.save(student);
    }

    @DeleteMapping
    public void deleteStudent (Long studentId){
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("Student with ID " + studentId + " does not exists");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent (Long studentId, String firstName, String lastName, String email, String phone){

        Student student = studentRepository.findById(studentId)
                .orElseThrow( () -> new IllegalStateException(
                        "Student with ID " +  studentId + " does not exists"
                ));
        if(firstName != null &&  firstName.length() > 0  && !Objects.equals(student.getFirstName(), firstName)){
            student.setFirstName(firstName);
        }
        if(lastName != null && lastName.length() > 0  && !Objects.equals(student.getLastName(), lastName)){
            student.setLastName(lastName);
        }

        if(email != null && email.length() >0 && !Objects.equals(student.getEmail(),email)){
             Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);

             if(studentOptional.isPresent()){
                 throw new IllegalStateException("Email already exists");
             }
             student.setEmail(email);
        }

        if(phone != null && phone.length() > 0  && !Objects.equals(student.getPhone(), lastName)){
            student.setPhone(phone);
        }


    }
}
