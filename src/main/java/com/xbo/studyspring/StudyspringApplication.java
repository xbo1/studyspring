package com.xbo.studyspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value="classpath:my.properties",encoding="utf-8")
public class StudyspringApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudyspringApplication.class, args);
    }
}
