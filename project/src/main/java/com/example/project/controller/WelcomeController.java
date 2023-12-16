package com.example.project.controller;

import com.example.project.service.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WelcomeController {
    @Autowired
    private final WelcomeService welcomeService;

    public WelcomeController(WelcomeService welcomeServiceImpl) {
        this.welcomeService = welcomeServiceImpl;
    }

    @RequestMapping("/")
    public @ResponseBody String welcome() {
        return welcomeService.welcome();
    }
}
