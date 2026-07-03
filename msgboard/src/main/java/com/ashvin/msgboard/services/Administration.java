package com.ashvin.msgboard.services;

import org.springframework.web.bind.annotation.*;       //@Controller
import org.springframework.beans.factory.annotation.*;        //@AutoWired
import org.springframework.stereotype.*;
import com.ashvin.msgboard.beans.*;

@Controller
public class Administration 
{
@Autowired
private DatabaseBean databaseBean;
@GetMapping("/admin")
public String adminIndex()
{
if(databaseBean.getDriver()!=null) return "AdminIndex";
return "Installation";
}
}
