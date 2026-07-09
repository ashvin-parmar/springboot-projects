package com.ashvin.msgboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import java.io.*;
import java.util.*;
import com.google.gson.*;
import com.ashvin.msgboard.beans.*;
import com.ashvin.msgboard.dto.*;
import com.ashvin.msgboard.dao.*;


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
Class.forName(databaseBean.getDriver());
DAOConnection.driver=databaseBean.getDriver();
DAOConnection.connectionString=databaseBean.getConnectionString();
DAOConnection.username=databaseBean.getUsername();
DAOConnection.password=databaseBean.getPassword();
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
@Bean
public MessageBoardBean getMessageBoardBean()
{
System.out.println("getMessageBoardBean got called");
MessageBoardBean messageBoardBean=new MessageBoardBean();
try
{
List<Branch> branches=(new BranchDAO()).getBranches();
List<Semester> semesters=(new SemesterDAO()).getSemesters();
List<BranchBean> branchBeans=new ArrayList<>();
List<SemesterBean> semesterBeans=new ArrayList<>();
BranchBean branchBean;
SemesterBean semesterBean;
for(Branch branch:branches)
{
branchBean=new BranchBean();
branchBean.setCode(branch.getCode());
branchBean.setName(branch.getName());
branchBeans.add(branchBean);
}
for(Semester semester:semesters)
{
semesterBean=new SemesterBean();
semesterBean.setCode(semester.getCode());
semesterBean.setName(semester.getName());
semesterBeans.add(semesterBean);
}
messageBoardBean.setBranches(branchBeans);
messageBoardBean.setSemesters(semesterBeans);
}catch(DAOException daoException)
{
System.out.println(daoException);
}
return messageBoardBean;
}
}
