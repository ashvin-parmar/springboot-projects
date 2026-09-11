package com.ashvin.parmar.learn.core.management;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;

@Controller
@Lazy
public class TestingSportController
{
private SportController sportController;

//field injection
@Autowired
private SportController sc1;
@Autowired
private SportController sc2;

//By default the couch selected is of @Primary annotated.
public TestingSportController(Couch couch)  //By default Primary Couch -> 'hockeyCouchManagement' will arrive
{
System.out.println("Testing Sport Controller initialized");
this.sportController=new SportController(couch);
}

@ResponseBody
@GetMapping("/sport/testDailyWorkout")
public String testSportDailyWorkout()
{
System.out.println("Checking is singleton or prototype with manual creation?: "+(this.sportController==this.sc1));
System.out.println("Checking is singleton or prototype from Spring Container?: "+(this.sc2==this.sc1));
return sportController.getSportDailyWorkout();
}

}

