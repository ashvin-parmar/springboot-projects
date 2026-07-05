package com.ashvin.msgboard.dao;

import com.ashvin.msgboard.dto.*;
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
System.out.println("administrator info added to table");
}catch(SQLException sqlException)
{
System.out.println(sqlException);
throw new DAOException("Unable to add administrator info");
}
}
}
