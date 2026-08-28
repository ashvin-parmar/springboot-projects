package com.ashvin.parmar.learn.basics.resourcefiles;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Controller
public class ResourceCheck
{
@Value("${JAVA_HOME}")
private String javaHome;
@Value("${my.name}")
private String myName;
@Value("${server.port}")
//     private String serverPort;
    private int serverPort;


@ResponseBody
@GetMapping("/resource/checkAttr")
public String settingUpAttributeCheckPerform()
{
return javaHome+" , "+myName+" , "+serverPort+"\n";
}

@GetMapping("/resource/templateCheck")
public String templateResourceCheckPerform()
{
System.out.println("here we go");
return "temp1";     //it will get the template temp1.html as output
}


}
