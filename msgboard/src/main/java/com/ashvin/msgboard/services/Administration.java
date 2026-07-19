package com.ashvin.msgboard.services;

import org.springframework.beans.factory.annotation.*;   //@Autowired
import org.springframework.stereotype.*;    //@Controller
import org.springframework.web.bind.annotation.*;   //@GetMapping
import org.springframework.ui.*;    //Model

import jakarta.servlet.*;
import jakarta.servlet.http.*; 

import com.ashvin.msgboard.utils.*;
import com.ashvin.msgboard.beans.*;
import com.ashvin.msgboard.dto.*;
import com.ashvin.msgboard.dao.*;

import java.io.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.*;

@Controller
public class Administration
{
@Autowired
private DatabaseBean databaseBean;
@Autowired
private MessageBoardBean messageBoardBean;

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
@GetMapping("/getBranches")
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
@GetMapping("/getSemesters")
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


@GetMapping("/students")
public String getStudents(Model model,HttpServletRequest request)
{
HttpSession ss=request.getSession(false);
if(ss==null || ss.getAttribute("username")==null) return "forward:/admin";

List<StudentView> students=null;
try
{
students=(new StudentDAO()).getStudents();
}catch(DAOException daoException)
{
students=new ArrayList<>();
System.out.println(daoException);
}
List<StudentViewBean> studentViewBeans=new ArrayList<>();
StudentViewBean studentViewBean;
BranchBean branchBean;
SemesterBean semesterBean;
Branch b;
Semester s;
for(StudentView student:students)
{
studentViewBean=new StudentViewBean();
studentViewBean.setFirstName(student.getFirstName());
studentViewBean.setLastName(student.getLastName());
studentViewBean.setRollNumber(student.getRollNumber());
studentViewBean.setEmailID(student.getEmailID());
b=student.getBranch();
branchBean=new BranchBean();
branchBean.setCode(b.getCode());
branchBean.setName(b.getName());

s=student.getSemester();     //clone
semesterBean=new SemesterBean();
semesterBean.setCode(s.getCode());
semesterBean.setName(s.getName());

studentViewBean.setBranch(branchBean);
studentViewBean.setSemester(semesterBean);
studentViewBeans.add(studentViewBean);
}

messageBoardBean.setStudents(studentViewBeans);
model.addAttribute("messageBoardBean",messageBoardBean);
return "Students";
}
@GetMapping("/home")
public String home(HttpServletRequest request)
{
HttpSession ss=request.getSession(false);
if(ss==null || ss.getAttribute("username")==null) return "forward:/admin";
return "Home";
}
@GetMapping("/notify")
public String notification(HttpServletRequest request)
{
HttpSession ss=request.getSession(false);
if(ss==null || ss.getAttribute("username")==null) return "forward:/admin";
return "Notification";
}

@GetMapping("/students/addForm")
public String getAddForm(HttpServletRequest request)
{
HttpSession ss=request.getSession(false);
if(ss==null || ss.getAttribute("username")==null) return "forward:/admin";
return "StudentAddForm";
}

@GetMapping("/students/editForm")
public String getEditForm(@RequestParam("emailID")String emailID,HttpServletRequest request,Model model)
{
HttpSession ss=request.getSession(false);
if(ss==null || ss.getAttribute("username")==null) return "forward:/admin";
try
{
StudentView student=(new StudentDAO()).getStudentByEmailID(emailID);
StudentViewBean studentViewBean;
BranchBean branchBean;
SemesterBean semesterBean;
Branch b;
Semester s;

studentViewBean=new StudentViewBean();
studentViewBean.setFirstName(student.getFirstName());
studentViewBean.setLastName(student.getLastName());
studentViewBean.setRollNumber(student.getRollNumber());
studentViewBean.setEmailID(student.getEmailID());
b=student.getBranch();
branchBean=new BranchBean();
branchBean.setCode(b.getCode());
branchBean.setName(b.getName());

s=student.getSemester();     //clone
semesterBean=new SemesterBean();
semesterBean.setCode(s.getCode());
semesterBean.setName(s.getName());

studentViewBean.setBranch(branchBean);
studentViewBean.setSemester(semesterBean);

model.addAttribute("studentBean",studentViewBean);
}catch(DAOException daoException)
{
NotificationBean notificationBean=new NotificationBean();
notificationBean.setHeading("Student (update module)");
notificationBean.setMessage(daoException.getMessage());
notificationBean.setHasToGenerateButtons(true);
notificationBean.setButtonOneText("OK");
notificationBean.setButtonOneAction("/students");
model.addAttribute("notification",notificationBean);
return "forward:/notify";
}
return "StudentEditForm";
}


@PostMapping("/login")
public String authenticateAdministrator(@RequestParam("username")String username,@RequestParam("password")String password,Model model,HttpServletRequest rq)
{
if(username==null || password==null || username.isBlank() || password.isBlank()) 
{
model.addAttribute("error","invalid username/password");
return "AdminIndex";
}
try
{
AdministratorDAO administratorDAO=new AdministratorDAO();
Administrator administrator=new Administrator();
administrator.setUsername(username);
administrator.setPassword(password);
if(administratorDAO.verifyUsernamePassword(administrator)) 
{
HttpSession ss=rq.getSession();
ss.setAttribute("username",username);
model.addAttribute("notification",new NotificationBean());
return "Home";
}
model.addAttribute("error","invalid username/password");
return "AdminIndex";
}catch(DAOException daoException)
{
System.out.println(daoException);
model.addAttribute("error","invalid username/password");
return "AdminIndex";
}
}
@ResponseBody
@PostMapping("/validateAdministratorLogin")
public String validateAuthenticateAdministratorLogin(HttpServletRequest request,HttpServletResponse response)
{
HttpSession ss=request.getSession(false);
try
{
if(ss==null) request.getRequestDispatcher("/admin").forward(request, response);
String username=(String)ss.getAttribute("username");
if(username==null || username.isBlank()) request.getRequestDispatcher("/admin").forward(request, response);
return username;
}catch(IOException ioException)
{
System.out.println(ioException);
}catch(ServletException servletException)
{
System.out.println(servletException);
}
return "";
}

@GetMapping("/logout")
public String logoutAdministrator(HttpSession ss)
{
if(ss!=null) ss.invalidate();
return "forward:/admin";
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
@PostMapping("/student/update")
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
@PostMapping("/student/delete")
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
@GetMapping("/student/getAll")
public ActionResponse getAllStudent()
{
ActionResponse actionResponse=new ActionResponse();
try
{
StudentDAO studentDAO=new StudentDAO();
List<StudentView> students=studentDAO.getStudents();

List<StudentViewBean> studentBeans=null;
Type listType = new TypeToken<List<StudentViewBean>>(){}.getType();
studentBeans=gson.fromJson(gson.toJson(students),listType);

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
@PostMapping("/student/getByRollNumber")
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
@PostMapping("/student/getByBranch")
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

List<StudentViewBean> studentBeans=null;
Type listType = new TypeToken<List<StudentViewBean>>(){}.getType();
studentBeans=gson.fromJson(gson.toJson(students),listType);

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
@PostMapping("/student/getBySemester")
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

List<StudentViewBean> studentBeans=null;
Type listType = new TypeToken<List<StudentViewBean>>(){}.getType();
studentBeans=gson.fromJson(gson.toJson(students),listType);

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
@PostMapping("/student/getByBranchAndSemester")
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

List<StudentViewBean> studentBeans=null;
Type listType = new TypeToken<List<StudentViewBean>>(){}.getType();
studentBeans=gson.fromJson(gson.toJson(students),listType);

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
