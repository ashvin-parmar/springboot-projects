package com.online.store.ecom.services;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;

import com.online.store.ecom.dl.pojo.*;
import com.online.store.ecom.dl.*;
import com.online.store.ecom.beans.*;

/**
 * @author Ashvin
 * @since 2026-08-07
 * Description: 
 */
@Controller
public class AdministratorService 
{
@Autowired
private AdministratorRepository administratorRepository;

@GetMapping("/addAdministrator")
public String addAdministrator(AdministratorBean administratorBean)
{

String passwordKey=java.util.UUID.randomUUID().toString().replaceAll("-","d");
Administrator administrator=new Administrator(administratorBean.getUsername(),administratorBean.getPassword(),passwordKey);

administratorRepository.save(administrator);
return "redirect:/index";
}
}

