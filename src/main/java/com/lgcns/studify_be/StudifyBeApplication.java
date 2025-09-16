package com.lgcns.studify_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class StudifyBeApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudifyBeApplication.class, args);
    }
}
