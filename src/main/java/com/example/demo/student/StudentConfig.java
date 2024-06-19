package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student abhoy = new Student(
                    "abhoy gorai",
                    "abhoygorai@gmail.com",
                    LocalDate.of(2002, Month.OCTOBER, 4)
            );
            Student ayush = new Student(
                    "Ayush Juyal",
                    "abhoygorai04@gmail.com",
                    LocalDate.of(2002, Month.OCTOBER, 4)
            );

            repository.saveAll(List.of(abhoy, ayush));
        };
    }
}