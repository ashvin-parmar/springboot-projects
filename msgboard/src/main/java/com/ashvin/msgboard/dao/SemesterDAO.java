package com.ashvin.msgboard.dao;

import java.sql.*;
import com.ashvin.msgboard.dto.*;
import java.util.*;

/**
 * @author Ashvin
 * @since 2026-07-08
 * Description: 
 */
public class SemesterDAO 
{
public SemesterDAO()
{
}
public void add(Semester semester) throws DAOException
{
if(semester==null) throw new DAOException("semester required");
if(semester.getName()==null) throw new DAOException("semester name required");
String name=semester.getName();
if(name.isBlank()) throw new DAOException("semester name required");
Connection connection=DAOConnection.getConnection();
try
{
PreparedStatement preparedStatement;
ResultSet resultSet;
preparedStatement=connection.prepareStatement("select code from semester where name=?");
preparedStatement.setString(1,name);
resultSet=preparedStatement.executeQuery();
boolean valid=!resultSet.next();
resultSet.close();
preparedStatement.close();
if(!valid)
{
throw new DAOException("semester name: "+name+" already exists");
}

preparedStatement=connection.prepareStatement("insert into semester (name) values(?)",Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString(1,name);
preparedStatement.executeUpdate();
resultSet=preparedStatement.getGeneratedKeys();
resultSet.next();
Integer code=resultSet.getInt(1);
resultSet.close();
preparedStatement.close();
connection.close();

semester.setCode(code);
}catch(SQLException sqlException)
{
System.out.println(sqlException);
throw new DAOException("unable to add semester: "+name);
}
}

public void update(Semester semester) throws DAOException
{
//validation
if(semester==null) throw new DAOException("semester required");
int code=semester.getCode();
if(code<=0) throw new DAOException("invalid semester data provided, not found");
String name=semester.getName();
if(name==null || name.isBlank()) throw new DAOException("semester name required");

Connection connection=DAOConnection.getConnection();
try
{
PreparedStatement preparedStatement;
ResultSet resultSet;
boolean valid;
preparedStatement=connection.prepareStatement("select code from semester where code=?");
preparedStatement.setInt(1,code);
resultSet=preparedStatement.executeQuery();
valid=resultSet.next();
resultSet.close();
preparedStatement.close();
if(!valid)
{
throw new DAOException("invalid semester data provided, not found");
}

preparedStatement=connection.prepareStatement("select semester_code from student_semester_mapping where semester_code=?");
preparedStatement.setInt(1,code);
resultSet=preparedStatement.executeQuery();
valid=!resultSet.next();
resultSet.close();
preparedStatement.close();
if(!valid)
{
throw new DAOException("unable to update semester record, since semester assigned to student record");
}

preparedStatement=connection.prepareStatement("update semester set name=? where code=?");
preparedStatement.setString(1,name);
preparedStatement.setInt(2,code);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
System.out.println(sqlException);
throw new DAOException("unable to update semester record");
}
}
public void delete(int code) throws DAOException
{
if(code<=0) throw new DAOException("invalid semester data provided, not found");
Connection connection=DAOConnection.getConnection();
try
{
PreparedStatement preparedStatement;
ResultSet resultSet;
boolean valid;
preparedStatement=connection.prepareStatement("select code from semester where code=?");
preparedStatement.setInt(1,code);
resultSet=preparedStatement.executeQuery();
valid=resultSet.next();
resultSet.close();
preparedStatement.close();
if(!valid)
{
throw new DAOException("invalid semester data provided, not found");
}

preparedStatement=connection.prepareStatement("select semester_code from student_semester_mapping where semester_code=?");
preparedStatement.setInt(1,code);
resultSet=preparedStatement.executeQuery();
valid=!resultSet.next();
resultSet.close();
preparedStatement.close();
if(!valid)
{
throw new DAOException("unable to delete semester record, since semester assigned to student record");
}

preparedStatement=connection.prepareStatement("delete from semester where code=?");
preparedStatement.setInt(1,code);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
System.out.println(sqlException);
throw new DAOException("unable to delete semester record");
}
}
public List<Semester> getSemesters() throws DAOException
{
List<Semester> semesters=new ArrayList<>();
Connection connection=DAOConnection.getConnection();
Semester semester;
int code;
String name;
try
{
PreparedStatement preparedStatement=connection.prepareStatement("select * from semester order by name");
ResultSet resultSet=preparedStatement.executeQuery();
while(resultSet.next())
{
code=resultSet.getInt("code");
name=resultSet.getString("name").trim();
semester=new Semester();
semester.setCode(code);
semester.setName(name);
semesters.add(semester);
}
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
System.out.println(sqlException);
throw new DAOException("unable to get semester record");
}
return semesters;
}
}
