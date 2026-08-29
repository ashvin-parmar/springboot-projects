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
@GetMapping("/usingDevTools")
public String usingDevTools()
{
return "changed java code does not required starting server again <BR> Using 'mvn compile' in new terminal provide new compiled form to already ready server. [New Change- Again Again]";
}
}

