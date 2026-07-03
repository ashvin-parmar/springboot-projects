package com.ashvin.msgboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import java.io.*;
import com.google.gson.*;
import com.ashvin.msgboard.beans.*;


@SpringBootApplication
public class MsgboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsgboardApplication.class, args);
	}
@Bean
public DatabaseBean getDatabaseBean()
{
System.out.println("getDatabaseBean got called");
File file=new File("conf"+File.separator+"db.json");
DatabaseBean databaseBean=null;
try
{
if(file.exists())
{
Gson gson=new Gson();
databaseBean=gson.fromJson(new FileReader(file.getAbsolutePath()),DatabaseBean.class);
}
else
{
databaseBean=new DatabaseBean();
}
}catch(Exception exception)
{
System.out.println(exception.getMessage());
}
return databaseBean;
}
}
