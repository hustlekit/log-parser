package com.hastesoft.logparser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class LogParserApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogParserApplication.class, args);
    }

}
