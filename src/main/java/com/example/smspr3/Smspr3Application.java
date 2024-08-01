package com.example.smspr3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //spring 감시하는 역할
@SpringBootApplication
public class Smspr3Application {

    public static void main(String[] args) {
        SpringApplication.run(Smspr3Application.class, args);
    }

}
