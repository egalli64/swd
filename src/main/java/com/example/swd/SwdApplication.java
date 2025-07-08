package com.example.swd;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.swd.m2.s4.CrudRegionRepository;

@SpringBootApplication
public class SwdApplication {
    private static final Logger log = LogManager.getLogger(SwdApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SwdApplication.class, args);
    }

    @Bean
    protected CommandLineRunner demo(CrudRegionRepository repository) {
        log.trace("Generating a bean that logs all regions (auto-executed at startup)");
        return args -> repository.findAll().forEach(region -> log.info(region.toString()));
    }
}
