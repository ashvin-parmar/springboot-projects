package com.ashvin.parmar.learn.core.management;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;

@Component("cricketCouchManagement")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CricketCouch implements Couch 
{
public CricketCouch()
{
System.out.println("Cricket Couch initialized");
}
@Override
public String getDailyWorkout()
{
return "Cricket Couch is selected, do bowling workout for 15 minutes";
}
}

