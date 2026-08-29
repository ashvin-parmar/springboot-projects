package com.ashvin.parmar.learn.core.injections;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;

@Controller
public class CricketController
{
private CricketCouch cricketCouch;

@Autowired
public CricketController(CricketCouch cricketCouch)
{
this.cricketCouch=cricketCouch;
}
@ResponseBody
@GetMapping("/getDailyWorkout")
public String getDailyWorkout()
{
return cricketCouch.getDailyWorkout();
}
}

