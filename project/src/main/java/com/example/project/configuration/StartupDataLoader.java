package com.example.project.configuration;

import com.example.project.entity.Student;
import com.example.project.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class StartupDataLoader {
    private final StudentRepository studentRepository;

    public StartupDataLoader(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Bean
    public CommandLineRunner loadInitialData() {
        return args -> {
            Student student1 = new Student("imie", "nazwisko", "kierunek");
            Student student2 = new Student("imie2", "nazwisko2", "kierunek2");

            studentRepository.saveAll(Arrays.asList(student1, student2));
        };
    }
}
