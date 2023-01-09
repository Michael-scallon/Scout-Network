package com.scout.scout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ScoutAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScoutAppApplication.class, args);
    }

}
