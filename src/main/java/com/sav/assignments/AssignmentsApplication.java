package com.sav.assignments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AssignmentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssignmentsApplication.class, args);
    }

}
