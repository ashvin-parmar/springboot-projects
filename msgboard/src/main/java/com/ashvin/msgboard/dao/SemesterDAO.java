package com.ashvin.msgboard.dao;

import java.sql.*;
import com.ashvin.msgboard.dto.*;
/**
 * @author Ashvin
 * @since 2026-07-08
 * Description: 
 */
public class SemesterDAO 
{
public SemesterDAO(){};
public void add(Semester semester) throws DAOException
{
if(semester==null) throw new DAOException("semester required");
if(semester.getName()==null) throw new DAOException("semester name required");
String name=semester.getName();
if(name.isBlank()) throw new DAOException("semester name required");
Connection connection=DAOConnection.getConnection();
try
{
PreparedStatement preparedStatement=connection.prepareStatement("insert into semester (name) values(?)",Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString(1,name);
preparedStatement.executeUpdate();
ResultSet resultSet=preparedStatement.getGeneratedKeys();
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
}

