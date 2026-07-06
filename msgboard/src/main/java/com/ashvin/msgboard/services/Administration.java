package com.ashvin.msgboard.services;

import org.springframework.beans.factory.annotation.*;   //@Autowired
import org.springframework.stereotype.*;    //@Controller
import org.springframework.web.bind.annotation.*;   //@GetMapping

import com.ashvin.msgboard.beans.*;
import com.ashvin.msgboard.utils.*;
import com.ashvin.msgboard.dto.*;
import com.ashvin.msgboard.dao.*;

import java.io.*;
import com.google.gson.*;
import java.util.*;

@Controller
public class Administration
{
@Autowired
private DatabaseBean databaseBean;
@GetMapping("/admin")
public String adminIndex()
{
if(databaseBean.getDriver()!=null) return "AdminIndex";
return "Installer";
}
@PostMapping("/install")
public String installMessageBoard(@RequestParam String driver,@RequestParam String connectionString,@RequestParam String username,@RequestParam String password,@RequestParam String administratorUsername,@RequestParam String administratorPassword)
{
//System.out.println(driver);
try
{
Class.forName(driver);
DAOConnection.driver=driver;
DAOConnection.connectionString=connectionString;
DAOConnection.username=username;
DAOConnection.password=password;

DatabaseUtility.createTables();
AdministratorDAO administratorDAO=new AdministratorDAO();
Administrator administrator=new Administrator();
administrator.setUsername(administratorUsername);
String administratorPasswordKey=EncryptionUtility.getKey();
String encryptedAdministratorPassword=EncryptionUtility.encrypt(administratorPassword,administratorPasswordKey);
administrator.setPassword(encryptedAdministratorPassword);
administrator.setPasswordKey(administratorPasswordKey);

administratorDAO.add(administrator);

//System.out.println("administrator info added to table");

//conf/db.json created
File file=new File("conf"+File.separator+"db.json");
JsonObject jsonObj=new JsonObject();
jsonObj.addProperty("driver",driver);
jsonObj.addProperty("connectionString",connectionString);
jsonObj.addProperty("username",username);
jsonObj.addProperty("password",password);
String jsonString=jsonObj.toString();
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
randomAccessFile.writeBytes(jsonString);
randomAccessFile.close();

return "InstallationSuccess";
}catch(Exception exception)
{
System.out.println(exception.getMessage());
return "InstallationFailed";
}
}
}
