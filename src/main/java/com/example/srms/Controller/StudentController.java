package com.example.srms.Controller;

import com.example.srms.Model.Student;
import com.example.srms.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/members/")
public class StudentController {


    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "get-members")
    public List<Student> students(){
        return studentService.getStudents();
    }

    @PostMapping(path = "create-member")
    public void registerStudent (@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "delete/{studentId}")
    public void deleteStudent (@PathVariable("studentId") Long studentId){

        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "update_student/{studentId}")

    public void updateStudent (@PathVariable("studentId") Long studentId,
                               @RequestParam(required = false) String firstName,
                               @RequestParam(required = false) String lastName,
                               @RequestParam(required = false) String email,
                               @RequestParam(required = false)  String phone
                              ){
        studentService.updateStudent(studentId,firstName,lastName, email,phone);
    }

}
