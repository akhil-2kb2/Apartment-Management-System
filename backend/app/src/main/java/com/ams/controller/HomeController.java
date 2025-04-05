package com.ams.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Apartment Management System Backend is Running!";
    }
}
// This code defines a simple Spring Boot REST controller that handles HTTP GET requests to the root URL ("/") of the API.