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
@ResponseBody
@PostMapping("/addBranch")
public ActionResponse addBranch(BranchBean branchBean)
{
ActionResponse actionResponse=new ActionResponse();
try
{
String name=branchBean.getName();
if(name==null || name.isBlank()) throw new DAOException("branch name required");

Branch branch=new Branch();
branch.setName(name);
BranchDAO branchDAO=new BranchDAO();
branchDAO.add(branch);
Integer code=branch.getCode();

actionResponse.setSuccess(true);
actionResponse.setException(null);
actionResponse.setResult(code);
}catch(DAOException daoException)
{
actionResponse.setSuccess(false);
actionResponse.setException(daoException.getMessage());
actionResponse.setResult(null);
}
return actionResponse;
}

@ResponseBody
@PostMapping("/updateBranch")
public ActionResponse updateBranch(BranchBean branchBean)
{
ActionResponse actionResponse=new ActionResponse();
try
{
int code=branchBean.getCode();
if(code<=0) throw new DAOException("invalid branch data provided, not found");
String name=branchBean.getName();
if(name==null || name.isBlank()) throw new DAOException("branch name required");

Branch branch=new Branch();
branch.setCode(code);
branch.setName(name);
BranchDAO branchDAO=new BranchDAO();
branchDAO.update(branch);
actionResponse.setSuccess(true);
actionResponse.setException(null);
actionResponse.setResult(null);
}catch(DAOException daoException)
{
actionResponse.setSuccess(false);
actionResponse.setException(daoException.getMessage());
actionResponse.setResult(null);
}
return actionResponse;
}
@ResponseBody
@PostMapping("/deleteBranch")
public ActionResponse deleteBranch(BranchBean branchBean)
{
ActionResponse actionResponse=new ActionResponse();
try
{
int code=branchBean.getCode();
if(code<=0) throw new DAOException("invalid branch data provided, not found");

BranchDAO branchDAO=new BranchDAO();
branchDAO.delete(code);
actionResponse.setSuccess(true);
actionResponse.setException(null);
actionResponse.setResult(null);
}catch(DAOException daoException)
{
actionResponse.setSuccess(false);
actionResponse.setException(daoException.getMessage());
actionResponse.setResult(null);
}
return actionResponse;
}
@ResponseBody
@PostMapping("/getBranches")
public ActionResponse getBranches()
{
ActionResponse actionResponse=new ActionResponse();
List<BranchBean> branchesBean=new ArrayList<>();
try
{
BranchDAO branchDAO=new BranchDAO();
List<Branch> branches=branchDAO.getBranches();
BranchBean branchBean;
for(Branch branch:branches)
{
branchBean=new BranchBean();
branchBean.setCode(branch.getCode());
branchBean.setName(branch.getName());
branchesBean.add(branchBean);
}
actionResponse.setSuccess(true);
actionResponse.setException(null);
actionResponse.setResult(branchesBean);
}catch(DAOException daoException)
{
actionResponse.setSuccess(false);
actionResponse.setException(daoException.getMessage());
actionResponse.setResult(branchesBean);
}
return actionResponse;
}



@ResponseBody
@PostMapping("/addSemester")
public ActionResponse addSemester(SemesterBean semesterBean)
{
ActionResponse actionResponse=new ActionResponse();
try
{
String name=semesterBean.getName();
if(name==null || name.isBlank()) throw new DAOException("semester name required");

Semester semester=new Semester();
semester.setName(name);
SemesterDAO semesterDAO=new SemesterDAO();
semesterDAO.add(semester);
Integer code=semester.getCode();

actionResponse.setSuccess(true);
actionResponse.setException(null);
actionResponse.setResult(code);
}catch(DAOException daoException)
{
actionResponse.setSuccess(false);
actionResponse.setException(daoException.getMessage());
actionResponse.setResult(null);
}
return actionResponse;
}
@ResponseBody
@PostMapping("/updateSemester")
public ActionResponse updateSemester(SemesterBean semesterBean)
{
ActionResponse actionResponse=new ActionResponse();
try
{
int code=semesterBean.getCode();
if(code<=0) throw new DAOException("invalid semester data provided, not found");
String name=semesterBean.getName();
if(name==null || name.isBlank()) throw new DAOException("semester name required");

Semester semester=new Semester();
semester.setCode(code);
semester.setName(name);
SemesterDAO semesterDAO=new SemesterDAO();
semesterDAO.update(semester);

actionResponse.setSuccess(true);
actionResponse.setException(null);
actionResponse.setResult(null);
}catch(DAOException daoException)
{
actionResponse.setSuccess(false);
actionResponse.setException(daoException.getMessage());
actionResponse.setResult(null);
}
return actionResponse;
}
@ResponseBody
@PostMapping("/deleteSemester")
public ActionResponse deleteSemester(SemesterBean semesterBean)
{
ActionResponse actionResponse=new ActionResponse();
try
{
int code=semesterBean.getCode();
if(code<=0) throw new DAOException("invalid semester data provided, not found");

SemesterDAO semesterDAO=new SemesterDAO();
semesterDAO.delete(code);

actionResponse.setSuccess(true);
actionResponse.setException(null);
actionResponse.setResult(null);
}catch(DAOException daoException)
{
actionResponse.setSuccess(false);
actionResponse.setException(daoException.getMessage());
actionResponse.setResult(null);
}
return actionResponse;
}
@ResponseBody
@PostMapping("/getSemesters")
public ActionResponse getSemesters()
{
ActionResponse actionResponse=new ActionResponse();
List<SemesterBean> semesterBeans=new ArrayList<>();
try
{
SemesterDAO semesterDAO=new SemesterDAO();
SemesterBean semesterBean;
List<Semester> semesters=semesterDAO.getSemesters();
for(Semester semester:semesters)
{
semesterBean=new SemesterBean();
semesterBean.setCode(semester.getCode());
semesterBean.setName(semester.getName());
semesterBeans.add(semesterBean);
}
actionResponse.setSuccess(true);
actionResponse.setException(null);
actionResponse.setResult(semesterBeans);
}catch(DAOException daoException)
{
actionResponse.setSuccess(false);
actionResponse.setException(daoException.getMessage());
actionResponse.setResult(semesterBeans);
}
return actionResponse;
}

}
