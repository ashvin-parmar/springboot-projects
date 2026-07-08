package com.ashvin.msgboard.dao;

import java.sql.*;
import com.ashvin.msgboard.dto.*;

/**
 * @author Ashvin
 * @since 2026-07-08
 * Description: 
 */
public class BranchDAO 
{
public BranchDAO(){};
public void add(Branch branch) throws DAOException
{
if(branch==null) throw new DAOException("branch required");
if(branch.getName()==null) throw new DAOException("branch name required");
String name=branch.getName();
if(name.isBlank()) throw new DAOException("branch name required");

Connection connection=DAOConnection.getConnection();
try
{
PreparedStatement preparedStatement=connection.prepareStatement("insert into branch (name) values(?)",Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString(1,name);
preparedStatement.executeUpdate();
ResultSet resultSet=preparedStatement.getGeneratedKeys();
resultSet.next();
resultSet.close();
preparedStatement.close();
connection.close();

Integer code=resultSet.getInt(1);
branch.setCode(code);

}catch(SQLException sqlException)
{
System.out.println(sqlException);       //later on add to log messages
throw new DAOException("Unable to add branch: "+name);
}
}    
}

