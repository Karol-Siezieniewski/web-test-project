package com.example.project.service;

import com.example.project.entity.Student;

import java.util.List;


public interface StudentService {
    List<Student> getAllStudents();
    Student addStudent(Student student);
}
