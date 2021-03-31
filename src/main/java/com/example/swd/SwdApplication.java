package com.example.swd;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.swd.dao.CoderRepo;

@SpringBootApplication
public class SwdApplication {
    private static final Logger log = LogManager.getLogger(SwdApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SwdApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CoderRepo repository) {
        return args -> repository.findAll().forEach(coder -> log.info(coder.toString()));
    }
}
