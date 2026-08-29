package com.ashvin.parmar.custom.scanning;

/**
 * @author Ashvin
 * @since 2026-08-29
 * Description: 
 */
import org.springframework.web.bind.annotation.*;


@RestController
public class SimpleController 
{
@GetMapping("testingExternalController")
public String simpleControllerOutput()
{
return "testing external package controller work with internal spring boot application or not. YES it is working.";
}    
}

