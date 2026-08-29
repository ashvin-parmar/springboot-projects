package com.ashvin.parmar.learn.core.management;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;

@Controller
public class SportController
{
private Couch couch;

//We have specified for 'cricketCouchManagement' bean from Spring container, means it will be selected for processing
@Autowired
public SportController(@Qualifier("cricketCouchManagement")Couch couch)    
{
this.couch=couch;
}

@ResponseBody
@GetMapping("/sport/getDailyWorkout")
public String getSportDailyWorkout()
{
return couch.getDailyWorkout();
}

}

