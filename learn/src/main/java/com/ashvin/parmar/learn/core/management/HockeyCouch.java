package com.ashvin.parmar.learn.core.management;

import org.springframework.stereotype.*;
import org.springframework.context.annotation.*;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;    //Scope available in both context and beans

@Component("hockeyCouchManagement")
@Primary        //Only one from all Couch implements may be set as primary. It treat as default selection for Couch type of Component selection
@Lazy
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HockeyCouch implements Couch 
{
public HockeyCouch()
{
System.out.println("Hockey couch initialized");
}
@Override
public String getDailyWorkout()
{
return "Hockey Couch is selected, Do running workout for 30 minutes";
}
}

