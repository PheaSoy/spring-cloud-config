package com.example.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppBean {

    @Autowired
    private Environment environment;

    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            String property = environment.getProperty("message.text", "n/a");
            String subject = environment.getProperty("message.subject", "n/a");
            System.out.println(property);
            System.out.println(subject);


        };
    }
}
