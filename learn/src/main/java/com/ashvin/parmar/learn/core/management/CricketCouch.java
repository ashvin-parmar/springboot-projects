package com.ashvin.parmar.learn.core.management;

import org.springframework.stereotype.*;

@Component("cricketCouchManagement")
public class CricketCouch implements Couch 
{
@Override
public String getDailyWorkout()
{
return "Cricket Couch is selected, do bowling workout for 15 minutes";
}
}

