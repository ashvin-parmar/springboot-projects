package com.ashvin.parmar.learn.core.management;

import jakarta.annotation.*;

//Not a Component
public class SwimCouch implements Couch 
{
public SwimCouch()
{
System.out.println("Swim couch initialized");
}

@PostConstruct
public void initialStartup()
{
System.out.println("initialization for object level constructions for SwimCouch");
}

@PreDestroy
public void cleanUpAct()
{
System.out.println("performing clean up act before destroy everything for SwimCouch");
}

@Override
public String getDailyWorkout()
{
return "Swim Couch is selected, Do diving workout for 30 minutes";
}
}

