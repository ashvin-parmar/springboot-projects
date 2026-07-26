package com.ashvin.msgboard.dao;

import com.ashvin.msgboard.dto.*;
import com.ashvin.msgboard.utils.*;
import java.sql.*;
public class AdministratorDAO
{
public void add(Administrator administrator) throws DAOException
{
Connection connection=DAOConnection.getConnection();
try
{
String administratorUsername=administrator.getUsername();
String administratorPassword=administrator.getPassword();
String administratorPasswordKey=administrator.getPasswordKey();
PreparedStatement preparedStatement=connection.prepareStatement("insert into administrator (username,password,password_key) values(?,?,?)");
preparedStatement.setString(1,administratorUsername);
preparedStatement.setString(2,administratorPassword);
preparedStatement.setString(3,administratorPasswordKey);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
System.out.println(sqlException);
throw new DAOException("Unable to add administrator info");
}
}
public boolean verifyUsernamePassword(Administrator givenAdmin) throws DAOException
{
if(givenAdmin==null) throw new DAOException("username not found");
String givenUsername=givenAdmin.getUsername();
String givenPassword=givenAdmin.getPassword();
if(givenUsername==null || givenPassword==null || givenUsername.isBlank() || givenPassword.isBlank()) throw new DAOException("invalid username/password");
String actualUsername;
String actualEncryptedPassword;
String actualPassword;
String actualPasswordKey;
PreparedStatement preparedStatement;
ResultSet resultSet;
Connection connection=DAOConnection.getConnection();
try
{
preparedStatement=connection.prepareStatement("select * from administrator where username=?");
preparedStatement.setString(1,givenUsername);
resultSet=preparedStatement.executeQuery();
if(!resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
//throw new DAOException("invalid username/password");
return false;
}
actualUsername=resultSet.getString("username").trim();
actualEncryptedPassword=resultSet.getString("password").trim();
actualPasswordKey=resultSet.getString("password_key").trim();
actualPassword=EncryptionUtility.decrypt(actualEncryptedPassword,actualPasswordKey);
resultSet.close();
preparedStatement.close();
connection.close();
if(!actualPassword.equals(givenPassword))
{
//throw new DAOException("invalid username/password");
return false;
}
return true;
}catch(DAOException daoException)
{
System.out.println(daoException);
//throw new DAOException("invalid username/password");
return false;
}catch(SQLException sqlException)
{
System.out.println(sqlException);
throw new DAOException("username not found");
}
}
}
