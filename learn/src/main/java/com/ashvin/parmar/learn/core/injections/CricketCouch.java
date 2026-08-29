package com.ashvin.parmar.learn.core.injections;

import org.springframework.stereotype.*;

@Component
public class CricketCouch implements Couch 
{
@Override
public String getDailyWorkout()
{
return "Practice bowling daily for 15 minutes, to get perfect in spinning the ball.";
}
}

