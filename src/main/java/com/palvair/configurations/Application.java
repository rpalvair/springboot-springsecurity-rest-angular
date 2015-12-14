package com.palvair.configurations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by PALVAIRR on 14/12/2015.
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        //springApplication.setAdditionalProfiles("inMemory");
        springApplication.run(args);
    }
}
