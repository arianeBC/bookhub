package com.example.bookhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookhubApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookhubApplication.class, args);
    }

}
