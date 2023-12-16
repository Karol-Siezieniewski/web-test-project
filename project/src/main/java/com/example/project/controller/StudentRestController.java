package com.example.project.controller;

import com.example.project.entity.Student;
import com.example.project.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {


    private final StudentService studentServiceImpl;

    @Autowired
    public StudentRestController(StudentService studentServiceImpl) {
        this.studentServiceImpl = studentServiceImpl;
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentServiceImpl.getAllStudents();
    }

    @PostMapping("/students/add")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student newStudent = studentServiceImpl.addStudent(student);

        return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);
    }

}
