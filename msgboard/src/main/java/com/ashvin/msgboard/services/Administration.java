package com.ashvin.msgboard.services;

import org.springframework.beans.factory.annotation.*;   //@Autowired
import org.springframework.stereotype.*;    //@Controller
import org.springframework.web.bind.annotation.*;   //@GetMapping
import com.ashvin.msgboard.beans.*;
import java.sql.*;
import java.io.*;
import com.google.gson.*;

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
System.out.println(driver);
try
{
Class.forName(driver);
Connection connection=DriverManager.getConnection(connectionString,username,password);
if(connection==null) return "InstallationFailed";
System.out.println("Connection establish");

//table create [pending]

PreparedStatement preparedStatement=connection.prepareStatement("insert into administrator (username,password,password_id) values(?,?,?)");
preparedStatement.setString(1,administratorUsername);
preparedStatement.setString(2,administratorPassword);
preparedStatement.setString(3,administratorPassword);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
System.out.println("administrator info added to table");

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
