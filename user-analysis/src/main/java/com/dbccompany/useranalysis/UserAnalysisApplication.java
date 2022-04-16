package com.dbccompany.useranalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UserAnalysisApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserAnalysisApplication.class, args);
    }

}
