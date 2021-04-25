package com.equalexperts.marsrover;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class MarsRoverApplication {
    public static void main(String[] args) {
        SpringApplication.run(MarsRoverApplication.class, args);
    }
}
