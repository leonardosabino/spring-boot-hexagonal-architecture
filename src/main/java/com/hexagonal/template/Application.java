package com.hexagonal.template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.hexagonal.template")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}