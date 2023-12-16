package com.example.project.controller;

import com.example.project.entity.Student;
import com.example.project.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@ExtendWith(MockitoExtension.class)
class StudentRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentRestController studentRestController;

    @BeforeEach
    void setUp() {
        mockMvc = standaloneSetup(studentRestController).build();
    }

    @Test
    void testGetAllStudents() throws Exception {
        // Given
        List<Student> students = Arrays.asList(
                new Student("Jacek", "Nowak", "Informatyka"),
                new Student("Filip", "Kowalski", "Fizyka stosowana")
        );

        // When
        when(studentService.getAllStudents()).thenReturn(students);

        // Then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(students.size()));
    }

    @Test
    void testGetAllStudentsEmptyList() throws Exception {
        // Given
        List<Student> students = List.of();

        // When
        when(studentService.getAllStudents()).thenReturn(students);

        // Then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0));
    }

    @Test
    void testAddStudent() throws Exception {
        // When
        Student newStudent = new Student("Jacek", "Kowalski", "IT");
        when(studentService.addStudent(any(Student.class))).thenReturn(newStudent);

        // Act
        ResultActions resultActions = mockMvc.perform(post("/api/students/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(newStudent)));

        // Then
        resultActions.andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("Jacek"))
                .andExpect(jsonPath("$.lastName").value("Kowalski"))
                .andExpect(jsonPath("$.fieldOfStudy").value("IT"));

        verify(studentService, times(1)).addStudent(any(Student.class));
    }

}