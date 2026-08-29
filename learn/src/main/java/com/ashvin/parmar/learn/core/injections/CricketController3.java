package com.ashvin.parmar.learn.core.injections;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;

@Controller
public class CricketController3
{
@Autowired      //field dependency injection - not recommended
private Couch couch;

@ResponseBody
@GetMapping("/getDailyWorkout3")
public String getDailyWorkout3()
{
return couch.getDailyWorkout();
}
}

