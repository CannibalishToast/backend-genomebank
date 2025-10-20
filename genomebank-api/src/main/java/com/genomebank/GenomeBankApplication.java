package com.genomebank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class GenomeBankApplication {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(GenomeBankApplication.class, args);
    }

    @PostConstruct
    public void printHash() {
        System.out.println(passwordEncoder.encode("mc4A5T8d"));
    }
}

