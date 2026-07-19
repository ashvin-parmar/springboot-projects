package com.ashvin.msgboard.dao;

import java.util.*;
import java.sql.*;
import com.ashvin.msgboard.dto.*;
import com.ashvin.msgboard.utils.*;

/**
 * @author Ashvin
 * @since 2026-07-10
 * Description: 
 */
public class StudentDAO 
{
private static long rn=100001;
public void add(Student student) throws DAOException
{
if(student==null) throw new DAOException("student data required");
String rollNumber;
String firstName=student.getFirstName();
String lastName=student.getLastName();
String emailID=student.getEmailID();
String password=student.getPassword();
String encryptedPassword;
String passwordKey;
int branchCode=student.getBranchCode();
int semesterCode=student.getSemesterCode();
if(firstName==null || firstName.isBlank()) throw new DAOException("student first name required");
if(lastName==null || lastName.isBlank()) throw new DAOException("student last name required");
if(emailID==null || emailID.isBlank()) throw new DAOException("student email id required");
if(!ValidateUtility.isValidEmail(emailID)) throw new DAOException("invalid student email id");
if(password==null || password.isBlank()) throw new DAOException("student password required");
if(!ValidateUtility.isStrongPassword(password)) throw new DAOException("please provide a strong password");
if(semesterCode<=0) throw new DAOException("student semester required");
if(branchCode<=0) throw new DAOException("student branch required");
Connection connection=DAOConnection.getConnection();
try
{
PreparedStatement preparedStatement;
ResultSet resultSet;
boolean valid;

preparedStatement=connection.prepareStatement("select roll_number from student where email_id=?");
preparedStatement.setString(1,emailID);
resultSet=preparedStatement.executeQuery();
valid=!resultSet.next();
resultSet.close();
preparedStatement.close();
if(!valid)
{
throw new DAOException("email id already exists");
}

preparedStatement=connection.prepareStatement("select code from branch where code=?");
preparedStatement.setInt(1,branchCode);
resultSet=preparedStatement.executeQuery();
valid=resultSet.next();
resultSet.close();
preparedStatement.close();
if(!valid)
{
throw new DAOException("student branch required");
}

preparedStatement=connection.prepareStatement("select code from semester where code=?");
preparedStatement.setInt(1,semesterCode);
resultSet=preparedStatement.executeQuery();
valid=resultSet.next();
resultSet.close();
preparedStatement.close();
if(!valid)
{
throw new DAOException("student semester required");
}

rollNumber="RN"+(rn++);
passwordKey=EncryptionUtility.getKey();
encryptedPassword=EncryptionUtility.encrypt(password,passwordKey);

preparedStatement=connection.prepareStatement("insert into student (roll_number,first_name,last_name,email_id,password,password_key) values(?,?,?,?,?,?)");
preparedStatement.setString(1,rollNumber);
preparedStatement.setString(2,firstName);
preparedStatement.setString(3,lastName);
preparedStatement.setString(4,emailID);
preparedStatement.setString(5,password);
preparedStatement.setString(6,passwordKey);
preparedStatement.executeUpdate();
preparedStatement.close();

preparedStatement=connection.prepareStatement("insert into student_branch_mapping (roll_number,branch_code) values(?,?)");
preparedStatement.setString(1,rollNumber);
preparedStatement.setInt(2,branchCode);
preparedStatement.executeUpdate();
preparedStatement.close();

preparedStatement=connection.prepareStatement("insert into student_semester_mapping (roll_number,semester_code) values(?,?)");
preparedStatement.setString(1,rollNumber);
preparedStatement.setInt(2,semesterCode);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();

}catch(SQLException sqlException)
{
System.out.println(sqlException);
throw new DAOException("unable to add student");
}
}
public void update(Student student) throws DAOException
{
if(student==null) throw new DAOException("student data required");
String rollNumber=student.getRollNumber();
String firstName=student.getFirstName();
String lastName=student.getLastName();
String emailID=student.getEmailID();

String password=student.getPassword();
String encryptedPassword;
String passwordKey;
int branchCode=student.getBranchCode();
int semesterCode=student.getSemesterCode();
if(rollNumber==null || rollNumber.isBlank()) throw new DAOException("student roll number required");

if(firstName==null || firstName.isBlank()) throw new DAOException("student first name required");
if(lastName==null || lastName.isBlank()) throw new DAOException("student last name required");
if(emailID==null || emailID.isBlank()) throw new DAOException("student email id required");
if(!ValidateUtility.isValidEmail(emailID)) throw new DAOException("invalid student email id");
if(password==null || password.isBlank()) throw new DAOException("student password required");
if(semesterCode<=0) throw new DAOException("student semester required");
if(branchCode<=0) throw new DAOException("student branch required");
Connection connection=DAOConnection.getConnection();
try
{
PreparedStatement preparedStatement;
ResultSet resultSet;
boolean valid;

preparedStatement=connection.prepareStatement("select code from branch where code=?");
preparedStatement.setInt(1,branchCode);
resultSet=preparedStatement.executeQuery();
valid=resultSet.next();
resultSet.close();
preparedStatement.close();
if(!valid)
{
throw new DAOException("student branch required");
}

preparedStatement=connection.prepareStatement("select code from semester where code=?");
preparedStatement.setInt(1,semesterCode);
resultSet=preparedStatement.executeQuery();
valid=resultSet.next();
resultSet.close();
preparedStatement.close();
if(!valid)
{
throw new DAOException("student semester required");
}


preparedStatement=connection.prepareStatement("select password,password_key from student where roll_number=? and email_id=?");
preparedStatement.setString(1,rollNumber);
preparedStatement.setString(2,emailID);
resultSet=preparedStatement.executeQuery();
valid=resultSet.next();
if(!valid)
{
resultSet.close();
preparedStatement.close();
throw new DAOException("cannot update student, not found");
}
encryptedPassword=resultSet.getString("password").trim();
passwordKey=resultSet.getString("password_key").trim();
resultSet.close();
preparedStatement.close();
String originalPassword=EncryptionUtility.decrypt(encryptedPassword,passwordKey);
if(!originalPassword.equals(password))
{
throw new DAOException("cannot update student, invalid password");
}


preparedStatement=connection.prepareStatement("update student set first_name=?, last_name=? where roll_number=?");
preparedStatement.setString(1,firstName);
preparedStatement.setString(2,lastName);
preparedStatement.setString(3,rollNumber);
preparedStatement.executeUpdate();
preparedStatement.close();

preparedStatement=connection.prepareStatement("update student_branch_mapping set branch_code=? where roll_number=?");
preparedStatement.setInt(1,branchCode);
preparedStatement.setString(2,rollNumber);
preparedStatement.executeUpdate();
preparedStatement.close();

preparedStatement=connection.prepareStatement("update student_semester_mapping set semester_code=? where roll_number=?");
preparedStatement.setInt(1,semesterCode);
preparedStatement.setString(2,rollNumber);
preparedStatement.executeUpdate();
preparedStatement.close();

connection.close();

}catch(SQLException sqlException)
{
System.out.println(sqlException);
throw new DAOException("unable to update student record");
}
}
public void delete(Student student) throws DAOException
{
if(student==null) throw new DAOException("student data required");
String rollNumber=student.getRollNumber();
String emailID=student.getEmailID();
String password=student.getPassword();
String encryptedPassword;
String passwordKey;
if(rollNumber==null || rollNumber.isBlank()) throw new DAOException("student roll number required");

if(emailID==null || emailID.isBlank()) throw new DAOException("student email id required");
if(!ValidateUtility.isValidEmail(emailID)) throw new DAOException("invalid student email id");
if(password==null || password.isBlank()) throw new DAOException("student password required");
Connection connection=DAOConnection.getConnection();
try
{
PreparedStatement preparedStatement;
ResultSet resultSet;
boolean valid;

preparedStatement=connection.prepareStatement("select password,password_key from student where roll_number=? and email_id=?");
preparedStatement.setString(1,rollNumber);
preparedStatement.setString(2,emailID);
resultSet=preparedStatement.executeQuery();
valid=resultSet.next();
if(!valid)
{
resultSet.close();
preparedStatement.close();
throw new DAOException("cannot delete student, not found");
}
encryptedPassword=resultSet.getString("password").trim();
passwordKey=resultSet.getString("password_key").trim();
resultSet.close();
preparedStatement.close();
String originalPassword=EncryptionUtility.decrypt(encryptedPassword,passwordKey);
if(!originalPassword.equals(password))
{
throw new DAOException("cannot delete student, invalid password");
}

preparedStatement=connection.prepareStatement("delete from student where roll_number=? and email_id=?");
preparedStatement.setString(1,rollNumber);
preparedStatement.setString(2,emailID);
preparedStatement.executeUpdate();
preparedStatement.close();

preparedStatement=connection.prepareStatement("delete from student_branch_mapping where roll_number=?");
preparedStatement.setString(1,rollNumber);
preparedStatement.executeUpdate();
preparedStatement.close();

preparedStatement=connection.prepareStatement("delete from student_semester_mapping where roll_number=?");
preparedStatement.setString(1,rollNumber);
preparedStatement.executeUpdate();
preparedStatement.close();

connection.close();

}catch(SQLException sqlException)
{
System.out.println(sqlException);
throw new DAOException("unable to delete student record");
}
}
public List<StudentView> getStudents() throws DAOException
{
List<StudentView> students=new ArrayList<StudentView>();
StudentView studentView;
Branch branch;
Semester semester;
Connection connection=DAOConnection.getConnection();
try
{
PreparedStatement preparedStatement;
ResultSet resultSet;
String firstName;
String lastName;
String emailID;
String rollNumber;
int branchCode;
int semesterCode;
String branchName;
String semesterName;
String sqlStatement="select student.first_name, student.last_name, student.roll_number, student.email_id,  branch.code as branch_code, branch.name as branch_name, semester.code as semester_code, semester.name as semester_name from student left join student_branch_mapping on student_branch_mapping.roll_number=student.roll_number left join student_semester_mapping on student_semester_mapping.roll_number=student.roll_number left join branch  on branch.code=student_branch_mapping.branch_code left join semester on semester.code=student_semester_mapping.semester_code order by student.roll_number;";
/*
SELECT 
    student.first_name, 
    student.last_name, 
    student.roll_number, 
    student.email_id, 
    
    -- Branch Info Lookups
    (SELECT student_branch_mapping.branch_code 
     FROM student_branch_mapping 
     WHERE student_branch_mapping.roll_number = student.roll_number) AS branch_code, 
     
    (SELECT branch.name 
     FROM branch 
     WHERE branch.code = (SELECT student_branch_mapping.branch_code 
                          FROM student_branch_mapping 
                          WHERE student_branch_mapping.roll_number = student.roll_number)) AS branch_name, 
     
    -- Semester Info Lookups
    (SELECT student_semester_mapping.semester_code 
     FROM student_semester_mapping 
     WHERE student_semester_mapping.roll_number = student.roll_number) AS semester_code, 
     
    (SELECT semester.name 
     FROM semester 
     WHERE semester.code = (SELECT student_semester_mapping.semester_code 
                            FROM student_semester_mapping 
                            WHERE student_semester_mapping.roll_number = student.roll_number)) AS semester_name 
FROM 
    student 
ORDER BY 
    student.roll_number;
*/
preparedStatement=connection.prepareStatement(sqlStatement);
resultSet=preparedStatement.executeQuery();
while(resultSet.next())
{
firstName=resultSet.getString("first_name").trim();
lastName=resultSet.getString("last_name").trim();
rollNumber=resultSet.getString("roll_number").trim();
emailID=resultSet.getString("email_id").trim();
branchCode=resultSet.getInt("branch_code");
branchName=resultSet.getString("branch_name").trim();
semesterCode=resultSet.getInt("semester_code");
semesterName=resultSet.getString("semester_name").trim();
branch=new Branch(branchCode,branchName);
semester=new Semester(semesterCode,semesterName);
studentView=new StudentView(firstName,lastName,rollNumber,emailID,branch,semester);
students.add(studentView);
}
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
System.out.println(sqlException);
throw new DAOException("unable to get student record");
}
return students;
}
public StudentView getStudentByRollNumber(String rollNumber) throws DAOException
{
if(rollNumber==null || rollNumber.isBlank()) throw new DAOException("roll number required");
StudentView studentView;
Branch branch;
Semester semester;
Connection connection=DAOConnection.getConnection();
try
{
PreparedStatement preparedStatement;
ResultSet resultSet;
boolean valid;
preparedStatement=connection.prepareStatement("select roll_number from student where roll_number=?");
preparedStatement.setString(1,rollNumber);
resultSet=preparedStatement.executeQuery();
valid=resultSet.next();
resultSet.close();
preparedStatement.close();
if(!valid)
{
connection.close();
throw new DAOException("invalid roll number: "+rollNumber);
}

String firstName;
String lastName;
String emailID;
int branchCode;
int semesterCode;
String branchName;
String semesterName;
String sqlStatement="select student.first_name, student.last_name, student.roll_number, student.email_id,  branch.code as branch_code, branch.name as branch_name, semester.code as semester_code, semester.name as semester_name from student ";
sqlStatement+="left join student_branch_mapping on student_branch_mapping.roll_number=student.roll_number ";
sqlStatement+="left join student_semester_mapping on student_semester_mapping.roll_number=student.roll_number ";
sqlStatement+="left join branch on branch.code=student_branch_mapping.branch_code ";
sqlStatement+="left join semester on semester.code=student_semester_mapping.semester_code ";
sqlStatement+="where student.roll_number=?;";
preparedStatement.setString(1,rollNumber);
preparedStatement=connection.prepareStatement(sqlStatement);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
firstName=resultSet.getString("first_name").trim();
lastName=resultSet.getString("last_name").trim();
rollNumber=resultSet.getString("roll_number").trim();
emailID=resultSet.getString("email_id").trim();
branchCode=resultSet.getInt("branch_code");
branchName=resultSet.getString("branch_name").trim();
semesterCode=resultSet.getInt("semester_code");
semesterName=resultSet.getString("semester_name").trim();
branch=new Branch(branchCode,branchName);
semester=new Semester(semesterCode,semesterName);
studentView=new StudentView(firstName,lastName,rollNumber,emailID,branch,semester);
}
else
{
resultSet.close();
preparedStatement.close();
throw new DAOException("unable to get student record, not found");
}
resultSet.close();
preparedStatement.close();
connection.close();
return studentView;
}catch(SQLException sqlException)
{
System.out.println(sqlException);
throw new DAOException("unable to get student record");
}
}
public StudentView getStudentByEmailID(String emailID) throws DAOException
{
if(emailID==null || emailID.isBlank()) throw new DAOException("email id required");
StudentView studentView;
Branch branch;
Semester semester;
Connection connection=DAOConnection.getConnection();
try
{
PreparedStatement preparedStatement;
ResultSet resultSet;
boolean valid;
preparedStatement=connection.prepareStatement("select roll_number from student where email_id=?");
preparedStatement.setString(1,emailID);
resultSet=preparedStatement.executeQuery();
valid=resultSet.next();
resultSet.close();
preparedStatement.close();
if(!valid)
{
connection.close();
throw new DAOException("invalid email id: "+emailID);
}

String firstName;
String lastName;
String rollNumber;
int branchCode;
int semesterCode;
String branchName;
String semesterName;
String sqlStatement="select student.first_name, student.last_name, student.roll_number, student.email_id,  branch.code as branch_code, branch.name as branch_name, semester.code as semester_code, semester.name as semester_name from student ";
sqlStatement+="left join student_branch_mapping on student_branch_mapping.roll_number=student.roll_number ";
sqlStatement+="left join student_semester_mapping on student_semester_mapping.roll_number=student.roll_number ";
sqlStatement+="left join branch on branch.code=student_branch_mapping.branch_code ";
sqlStatement+="left join semester on semester.code=student_semester_mapping.semester_code ";
sqlStatement+="where student.email_id=?;";
preparedStatement.setString(1,emailID);
preparedStatement=connection.prepareStatement(sqlStatement);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
firstName=resultSet.getString("first_name").trim();
lastName=resultSet.getString("last_name").trim();
rollNumber=resultSet.getString("roll_number").trim();
emailID=resultSet.getString("email_id").trim();
branchCode=resultSet.getInt("branch_code");
branchName=resultSet.getString("branch_name").trim();
semesterCode=resultSet.getInt("semester_code");
semesterName=resultSet.getString("semester_name").trim();
branch=new Branch(branchCode,branchName);
semester=new Semester(semesterCode,semesterName);
studentView=new StudentView(firstName,lastName,rollNumber,emailID,branch,semester);
}
else
{
resultSet.close();
preparedStatement.close();
throw new DAOException("unable to get student record, not found");
}
resultSet.close();
preparedStatement.close();
connection.close();
return studentView;
}catch(SQLException sqlException)
{
System.out.println(sqlException);
throw new DAOException("unable to get student record");
}
}

public List<StudentView> getStudentsByBranchCode(int branchCode) throws DAOException
{
if(branchCode<=0) throw new DAOException("student(s) branch required");
StudentView studentView;
Branch branch;
Semester semester;
Connection connection=DAOConnection.getConnection();
try
{
PreparedStatement preparedStatement;
ResultSet resultSet;
boolean valid;
preparedStatement=connection.prepareStatement("select code from branch where code=?");
preparedStatement.setInt(1,branchCode);
resultSet=preparedStatement.executeQuery();
valid=resultSet.next();
resultSet.close();
preparedStatement.close();
if(!valid)
{
throw new DAOException("student(s) branch required");
}

String firstName;
String lastName;
String emailID;
String rollNumber;
int semesterCode;
String branchName;
String semesterName;
List<StudentView> students=new ArrayList<StudentView>();
String sqlStatement="select student.first_name, student.last_name, student.roll_number, student.email_id,  branch.code as branch_code, branch.name as branch_name, semester.code as semester_code, semester.name as semester_name from student ";
sqlStatement+="inner join student_branch_mapping on student_branch_mapping.roll_number=student.roll_number ";
sqlStatement+="left join student_semester_mapping on student_semester_mapping.roll_number=student.roll_number ";
sqlStatement+="inner join branch on branch.code=student_branch_mapping.branch_code ";
sqlStatement+="left join semester on semester.code=student_semester_mapping.semester_code ";
sqlStatement+="where student_branch_mapping.branch_code=? ";
sqlStatement+="order by student.roll_number;";
preparedStatement.setInt(1,branchCode);
preparedStatement=connection.prepareStatement(sqlStatement);
resultSet=preparedStatement.executeQuery();
while(resultSet.next())
{
firstName=resultSet.getString("first_name").trim();
lastName=resultSet.getString("last_name").trim();
rollNumber=resultSet.getString("roll_number").trim();
emailID=resultSet.getString("email_id").trim();
branchCode=resultSet.getInt("branch_code");
branchName=resultSet.getString("branch_name").trim();
semesterCode=resultSet.getInt("semester_code");
semesterName=resultSet.getString("semester_name").trim();
branch=new Branch(branchCode,branchName);
semester=new Semester(semesterCode,semesterName);
studentView=new StudentView(firstName,lastName,rollNumber,emailID,branch,semester);
students.add(studentView);
}
resultSet.close();
preparedStatement.close();
connection.close();
return students;
}catch(SQLException sqlException)
{
System.out.println(sqlException);
throw new DAOException("unable to get student record");
}
}
public List<StudentView> getStudentsBySemesterCode(int semesterCode) throws DAOException
{
if(semesterCode<=0) throw new DAOException("student(s) semester required");
StudentView studentView;
Branch branch;
Semester semester;
Connection connection=DAOConnection.getConnection();
try
{
PreparedStatement preparedStatement;
ResultSet resultSet;
boolean valid;
preparedStatement=connection.prepareStatement("select code from semester where code=?");
preparedStatement.setInt(1,semesterCode);
resultSet=preparedStatement.executeQuery();
valid=resultSet.next();
resultSet.close();
preparedStatement.close();
if(!valid)
{
throw new DAOException("student(s) semester required");
}

String firstName;
String lastName;
String emailID;
String rollNumber;
int branchCode;
String branchName;
String semesterName;
List<StudentView> students=new ArrayList<StudentView>();
String sqlStatement="select student.first_name, student.last_name, student.roll_number, student.email_id,  branch.code as branch_code, branch.name as branch_name, semester.code as semester_code, semester.name as semester_name from student ";
sqlStatement+="left join student_branch_mapping on student_branch_mapping.roll_number=student.roll_number ";
sqlStatement+="inner join student_semester_mapping on student_semester_mapping.roll_number=student.roll_number ";
sqlStatement+="left join branch on branch.code=student_branch_mapping.branch_code ";
sqlStatement+="inner join semester on semester.code=student_semester_mapping.semester_code ";
sqlStatement+="where student_semester_mapping.semester_code=? ";
sqlStatement+="order by student.roll_number;";
preparedStatement.setInt(1,semesterCode);
preparedStatement=connection.prepareStatement(sqlStatement);
resultSet=preparedStatement.executeQuery();
while(resultSet.next())
{
firstName=resultSet.getString("first_name").trim();
lastName=resultSet.getString("last_name").trim();
rollNumber=resultSet.getString("roll_number").trim();
emailID=resultSet.getString("email_id").trim();
branchCode=resultSet.getInt("branch_code");
branchName=resultSet.getString("branch_name").trim();
semesterCode=resultSet.getInt("semester_code");
semesterName=resultSet.getString("semester_name").trim();
branch=new Branch(branchCode,branchName);
semester=new Semester(semesterCode,semesterName);
studentView=new StudentView(firstName,lastName,rollNumber,emailID,branch,semester);
students.add(studentView);
}
resultSet.close();
preparedStatement.close();
connection.close();
return students;
}catch(SQLException sqlException)
{
System.out.println(sqlException);
throw new DAOException("unable to get student record");
}
}
public List<StudentView> getStudentsByBranchAndSemesterCode(int branchCode,int semesterCode) throws DAOException
{
if(branchCode<=0) throw new DAOException("student(s) branch required");
if(semesterCode<=0) throw new DAOException("student(s) semester required");
StudentView studentView;
Branch branch=null;
Semester semester=null;
Connection connection=DAOConnection.getConnection();
try
{
PreparedStatement preparedStatement;
ResultSet resultSet;
boolean valid;
preparedStatement=connection.prepareStatement("select code from branch where code=?");
preparedStatement.setInt(1,branchCode);
resultSet=preparedStatement.executeQuery();
valid=resultSet.next();
resultSet.close();
preparedStatement.close();
if(!valid)
{
throw new DAOException("student(s) branch required");
}

preparedStatement=connection.prepareStatement("select code from semester where code=?");
preparedStatement.setInt(1,semesterCode);
resultSet=preparedStatement.executeQuery();
valid=resultSet.next();
resultSet.close();
preparedStatement.close();
if(!valid)
{
throw new DAOException("student(s) semester required");
}

String firstName;
String lastName;
String emailID;
String rollNumber;
String branchName;
String semesterName;
List<StudentView> students=new ArrayList<StudentView>();
String sqlStatement="select student.first_name, student.last_name, student.roll_number, student.email_id,  branch.code as branch_code, branch.name as branch_name, semester.code as semester_code, semester.name as semester_name from student ";
sqlStatement+="inner join student_branch_mapping on student_branch_mapping.roll_number=student.roll_number ";
sqlStatement+="inner join student_semester_mapping on student_semester_mapping.roll_number=student.roll_number ";
sqlStatement+="inner join branch on branch.code=student_branch_mapping.branch_code ";
sqlStatement+="inner join semester on semester.code=student_semester_mapping.semester_code ";
sqlStatement+="where student_semester_mapping.semester_code=? ";
sqlStatement+="order by student.roll_number;";
preparedStatement.setInt(1,semesterCode);
preparedStatement=connection.prepareStatement(sqlStatement);
resultSet=preparedStatement.executeQuery();
while(resultSet.next())
{
firstName=resultSet.getString("first_name").trim();
lastName=resultSet.getString("last_name").trim();
rollNumber=resultSet.getString("roll_number").trim();
emailID=resultSet.getString("email_id").trim();
if(branch==null)
{
    branchName=resultSet.getString("branch_name").trim();
    semesterName=resultSet.getString("semester_name").trim();
    branch=new Branch(branchCode,branchName);
    semester=new Semester(semesterCode,semesterName);
}
studentView=new StudentView(firstName,lastName,rollNumber,emailID,branch,semester);
students.add(studentView);
}
resultSet.close();
preparedStatement.close();
connection.close();
return students;
}catch(SQLException sqlException)
{
System.out.println(sqlException);
throw new DAOException("unable to get student record");
}
}
}
