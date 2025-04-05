package com.ams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApartmentManagementSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApartmentManagementSystemApplication.class, args);
        System.out.println("Apartment Management System Backend is Running!");
    }
}

