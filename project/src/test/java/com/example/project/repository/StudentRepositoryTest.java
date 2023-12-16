package com.example.project.repository;

import com.example.project.entity.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentRepositoryTest {

    @Mock
    private StudentRepository studentRepository;

    @Test
    void testFindByLastName() {
        // Given
        String lastName = "Kowalski";
        Student student = new Student("Jan", "Kowalski", "Informatyka");
        List<Student> expectedStudents = Arrays.asList(student);

        when(studentRepository.findByLastName(lastName)).thenReturn(expectedStudents);

        // When
        List<Student> actualStudents = studentRepository.findByLastName(lastName);

        // Then
        assertEquals(expectedStudents, actualStudents);
        verify(studentRepository, times(1)).findByLastName(lastName);
    }

    @Test
    void testFindById() {
        // Given
        long studentId = 1L;
        Student student = new Student("Michal", "Nowak", "Prawo");

        when(studentRepository.findById(studentId)).thenReturn(student);

        // When
        Optional<Student> foundStudent = Optional.ofNullable(studentRepository.findById(studentId));

        // Then
        assertTrue(foundStudent.isPresent());
        assertEquals(student, foundStudent.get());
        verify(studentRepository, times(1)).findById(studentId);
    }

}