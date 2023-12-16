package com.example.project.service;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class WelcomeService {

    public String welcome() {
        return "Wilkommen!";
    }
}
