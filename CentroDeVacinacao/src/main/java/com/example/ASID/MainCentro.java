package com.example.ASID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.example.ASID")
@EnableJpaRepositories(basePackages = "com.example.ASID")
@EnableDiscoveryClient
public class MainCentro {

    public static void main(String[] args) {
        SpringApplication.run(MainCentro.class, args);
    }

}