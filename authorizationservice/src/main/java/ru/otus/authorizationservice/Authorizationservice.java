package ru.otus.authorizationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Authorizationservice {

    public static void main(String[] args) {
        SpringApplication.run(Authorizationservice.class, args);
    }

}
