package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties
public class AppConfigStart {
    public static void main(String[] args) {
        SpringApplication.run(AppConfigStart.class, args);
    }

}