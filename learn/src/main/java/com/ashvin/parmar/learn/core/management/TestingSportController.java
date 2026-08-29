package com.ashvin.parmar.learn.core.management;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;

@Controller
public class TestingSportController
{
private SportController sportController;

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
return sportController.getSportDailyWorkout();
}

}

