package com.ashvin.parmar.learn.core.management;

import org.springframework.stereotype.*;
import org.springframework.context.annotation.*;

@Component("hockeyCouchManagement")
@Primary        //Only one from all Couch implements may be set as primary. It treat as default selection for Couch type of Component selection
public class HockeyCouch implements Couch 
{
@Override
public String getDailyWorkout()
{
return "Hockey Couch is selected, Do running workout for 30 minutes";
}
}

