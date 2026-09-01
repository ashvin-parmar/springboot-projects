package com.ashvin.parmar.learn.core.management;

import org.springframework.context.annotation.*;

/**
 * @author Ashvin
 * @since 2026-09-01
 * Description: 
 */
@Configuration
public class SportConfig 
{
    @Bean("swimCouchManagement")        //set or default method name
    public Couch swimCouchManagement()
    {
        return new SwimCouch();
    }
}

