package com.keyin.fsdsaid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.keyin.fsdsaid")
public class main {
    public static void main(String[] args) {
        SpringApplication.run(main.class, args);
    }
}
