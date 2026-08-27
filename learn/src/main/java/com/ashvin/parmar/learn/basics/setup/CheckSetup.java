package com.ashvin.parmar.learn.basics.setup;

import org.springframework.web.bind.annotation.*;

@RestController
public class CheckSetup
{
@GetMapping("/")
public String initialization()
{
return "Welcome! Setup Done!";
}
}

