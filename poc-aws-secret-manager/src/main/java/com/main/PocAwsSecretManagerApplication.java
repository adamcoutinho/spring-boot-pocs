package com.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PocAwsSecretManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PocAwsSecretManagerApplication.class, args);
    }

}
