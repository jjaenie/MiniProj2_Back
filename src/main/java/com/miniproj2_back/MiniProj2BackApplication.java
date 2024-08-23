package com.miniproj2_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.miniproj2_back"})
@EnableJpaRepositories(basePackages = "com.miniproj2_back.repository")
public class MiniProj2BackApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniProj2BackApplication.class, args);
    }

}
