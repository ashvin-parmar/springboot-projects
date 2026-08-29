package com.ashvin.parmar.learn.core.injections;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;

@Controller
public class CricketController2
{
private Couch couch;

@Autowired
public void setCouch(Couch couch)   //standard method name should be 'setCouch(Couch couch)'
{
this.couch=couch;
}

@ResponseBody
@GetMapping("/getDailyWorkout2")
public String getDailyWorkout2()
{
return couch.getDailyWorkout();
}
}

