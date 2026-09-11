package com.ashvin.parmar.learn;

import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.*;
import org.springframework.context.annotation.*;

@SpringBootApplication(scanBasePackages={"com.ashvin.parmar.learn","com.ashvin.parmar.custom"})
public class LearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnApplication.class, args);
	}

    @Bean 
    public CommandLineRunner commandLineRunner(String[] args)
    {
        return runner -> {
            System.out.println("Hello, Here we start our learning JPA journey!");
        };
    }

}
