package com.ashvin.msgboard.services;

import org.springframework.beans.factory.annotation.*;   //@Autowired
import org.springframework.stereotype.*;    //@Controller
import org.springframework.web.bind.annotation.*;   //@GetMapping

import com.ashvin.msgboard.utils.*;
import com.ashvin.msgboard.beans.*;
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
private Gson gson=new Gson();
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

@PostMapping("/authenticateAdministrator")
public String authenticateAdministrator(AdministratorBean administratorBean)
{
boolean invalid=false;
if(administratorBean==null) invalid=true;
String username=administratorBean.getUsername();
String password=administratorBean.getPassword();
if(username==null || password==null || username.isBlank() || password.isBlank()) invalid=true;
if(invalid) return "AdminIndex";
try
{
AdministratorDAO administratorDAO=new AdministratorDAO();
Administrator administrator=new Administrator();
administrator.setUsername(username);
administrator.setPassword(password);
if(administratorDAO.verifyUsernamePassword(administrator)) return "Home";
return "AdminIndex";
}catch(DAOException daoException)
{
System.out.println(daoException);
return "AdminIndex";
}
}

@ResponseBody
@PostMapping("/student/add")
public ActionResponse addStudent(StudentBean studentBean)
{
ActionResponse actionResponse=new ActionResponse();
boolean valid=true;
String firstName=studentBean.getFirstName();
String lastName=studentBean.getLastName();
String rollNumber=null;
String emailID=studentBean.getEmailID();
String password=studentBean.getPassword();
int branchCode=studentBean.getBranchCode();
int semesterCode=studentBean.getSemesterCode();
if(firstName==null || firstName.isBlank()) valid=false;
if(lastName==null || lastName.isBlank()) valid=false;
if(emailID==null || emailID.isBlank() ) valid=false;        //also check for email validation later on
if(password==null || password.isBlank()) valid=false;
if(branchCode<=0) valid=false;
if(semesterCode<=0) valid=false;
//More validation later on
if(!valid)      // error management is not perfect, changes later on
{
actionResponse.setSuccess(false);
actionResponse.setException("invalid student data provided");
actionResponse.setResult(null);
return actionResponse;
}
try
{
StudentDAO studentDAO=new StudentDAO();
Student student=new Student();
student.setFirstName(firstName);
student.setLastName(lastName);
student.setEmailID(emailID);
student.setPassword(password);
student.setBranchCode(branchCode);
student.setSemesterCode(semesterCode);
studentDAO.add(student);
rollNumber=student.getRollNumber();

actionResponse.setSuccess(true);
actionResponse.setException(null);
actionResponse.setResult(rollNumber);
}catch(DAOException daoException)
{
actionResponse.setSuccess(false);
actionResponse.setException(daoException.getMessage());
actionResponse.setResult(null);
}
return actionResponse;
}

@ResponseBody
@PostMapping("/stuedent/update")
public ActionResponse updateStudent(StudentBean studentBean)
{
ActionResponse actionResponse=new ActionResponse();
boolean valid=true;
String firstName=studentBean.getFirstName();
String lastName=studentBean.getLastName();
String rollNumber=studentBean.getRollNumber();
String password=studentBean.getPassword();
String emailID=studentBean.getEmailID();
int branchCode=studentBean.getBranchCode();
int semesterCode=studentBean.getSemesterCode();
if(firstName==null || firstName.isBlank()) valid=false;
if(lastName==null || lastName.isBlank()) valid=false;
if(rollNumber==null || rollNumber.isBlank()) valid=false;
if(emailID==null || emailID.isBlank() ) valid=false;        //also check for email validation later on
if(password==null || password.isBlank()) valid=false;
if(branchCode<=0) valid=false;
if(semesterCode<=0) valid=false;
//More validation later on
if(!valid)      // error management is not perfect, changes later on
{
actionResponse.setSuccess(false);
actionResponse.setException("invalid student data provided");
actionResponse.setResult(null);
return actionResponse;
}

try
{
StudentDAO studentDAO=new StudentDAO();
Student student=new Student();
student.setRollNumber(rollNumber);
student.setFirstName(firstName);
student.setLastName(lastName);
student.setEmailID(emailID);
student.setPassword(password);
student.setBranchCode(branchCode);
student.setSemesterCode(semesterCode);
studentDAO.update(student);

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
@PostMapping("/stuedent/delete")
public ActionResponse deleteStudent(StudentBean studentBean)
{
ActionResponse actionResponse=new ActionResponse();
boolean valid=true;
String rollNumber=studentBean.getRollNumber();
String password=studentBean.getPassword();
String emailID=studentBean.getEmailID();
if(rollNumber==null || rollNumber.isBlank()) valid=false;
if(emailID==null || emailID.isBlank() ) valid=false;        //also check for email validation later on
if(password==null || password.isBlank()) valid=false;
//More validation later on
if(!valid)      // error management is not perfect, changes later on
{
actionResponse.setSuccess(false);
actionResponse.setException("invalid student data provided");
actionResponse.setResult(null);
return actionResponse;
}

try
{
StudentDAO studentDAO=new StudentDAO();
Student student=new Student();
student.setRollNumber(rollNumber);
student.setEmailID(emailID);
student.setPassword(password);
studentDAO.delete(student);

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
@PostMapping("/stuedent/getAll")
public ActionResponse getAllStudent()
{
ActionResponse actionResponse=new ActionResponse();
try
{
StudentDAO studentDAO=new StudentDAO();
List<StudentView> students=studentDAO.getStudents();

List<StudentViewBean> studentBeans=new ArrayList<>();
studentBeans=gson.fromJson(gson.toJson(students),studentBeans.getClass());

actionResponse.setSuccess(true);
actionResponse.setException(null);
actionResponse.setResult(studentBeans);
}catch(DAOException daoException)
{
actionResponse.setSuccess(false);
actionResponse.setException(daoException.getMessage());
actionResponse.setResult(null);
}
return actionResponse;
}

@ResponseBody
@PostMapping("/stuedent/getByRollNumber")
public ActionResponse getStudentByRollNumber(@RequestParam("rollNumber")String rollNumber)
{
ActionResponse actionResponse=new ActionResponse();
if(rollNumber==null || rollNumber.isBlank())
{
actionResponse.setSuccess(false);
actionResponse.setException("roll number required");
actionResponse.setResult(null);
return actionResponse;
}
try
{
StudentDAO studentDAO=new StudentDAO();
StudentView studentView=studentDAO.getStudentByRollNumber(rollNumber);

StudentViewBean studentViewBean=null;
studentViewBean=gson.fromJson(gson.toJson(studentView),StudentViewBean.class);

actionResponse.setSuccess(true);
actionResponse.setException(null);
actionResponse.setResult(studentViewBean);
}catch(DAOException daoException)
{
actionResponse.setSuccess(false);
actionResponse.setException(daoException.getMessage());
actionResponse.setResult(null);
}
return actionResponse;
}

@ResponseBody
@PostMapping("/stuedent/getByBranch")
public ActionResponse getStudentByBranch(@RequestParam("branchCode") int branchCode)
{
ActionResponse actionResponse=new ActionResponse();
if(branchCode<=0)
{
actionResponse.setSuccess(false);
actionResponse.setException("student branch required");
actionResponse.setResult(null);
return actionResponse;
}
try
{
StudentDAO studentDAO=new StudentDAO();
List<StudentView> students=studentDAO.getStudentsByBranchCode(branchCode);

List<StudentViewBean> studentBeans=new ArrayList<>();
studentBeans=gson.fromJson(gson.toJson(students),studentBeans.getClass());

actionResponse.setSuccess(true);
actionResponse.setException(null);
actionResponse.setResult(studentBeans);
}catch(DAOException daoException)
{
actionResponse.setSuccess(false);
actionResponse.setException(daoException.getMessage());
actionResponse.setResult(null);
}
return actionResponse;
}
@ResponseBody
@PostMapping("/stuedent/getBySemester")
public ActionResponse getStudentBySemester(@RequestParam("semesterCode") int semesterCode)
{
ActionResponse actionResponse=new ActionResponse();
if(semesterCode<=0)
{
actionResponse.setSuccess(false);
actionResponse.setException("student semester required");
actionResponse.setResult(null);
return actionResponse;
}
try
{
StudentDAO studentDAO=new StudentDAO();
List<StudentView> students=studentDAO.getStudentsBySemesterCode(semesterCode);

List<StudentViewBean> studentBeans=new ArrayList<>();
studentBeans=gson.fromJson(gson.toJson(students),studentBeans.getClass());

actionResponse.setSuccess(true);
actionResponse.setException(null);
actionResponse.setResult(studentBeans);
}catch(DAOException daoException)
{
actionResponse.setSuccess(false);
actionResponse.setException(daoException.getMessage());
actionResponse.setResult(null);
}
return actionResponse;
}
@ResponseBody
@PostMapping("/stuedent/getByBranchAndSemester")
public ActionResponse getStudentByBranchAndSemester(@RequestParam("branchCode") int branchCode,@RequestParam("semesterCode") int semesterCode)
{
ActionResponse actionResponse=new ActionResponse();
if(branchCode<=0)
{
actionResponse.setSuccess(false);
actionResponse.setException("student branch required");
actionResponse.setResult(null);
return actionResponse;
}
if(semesterCode<=0)
{
actionResponse.setSuccess(false);
actionResponse.setException("student semester required");
actionResponse.setResult(null);
return actionResponse;
}
try
{
StudentDAO studentDAO=new StudentDAO();
List<StudentView> students=studentDAO.getStudentsByBranchAndSemesterCode(branchCode,semesterCode);

List<StudentViewBean> studentBeans=new ArrayList<>();
studentBeans=gson.fromJson(gson.toJson(students),studentBeans.getClass());

actionResponse.setSuccess(true);
actionResponse.setException(null);
actionResponse.setResult(studentBeans);
}catch(DAOException daoException)
{
actionResponse.setSuccess(false);
actionResponse.setException(daoException.getMessage());
actionResponse.setResult(null);
}
return actionResponse;
}

}
