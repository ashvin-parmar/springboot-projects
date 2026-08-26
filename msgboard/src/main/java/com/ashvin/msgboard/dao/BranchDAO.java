package com.ashvin.msgboard.dao;

import java.sql.*;
import com.ashvin.msgboard.dto.*;
import java.util.*;

/**
 * @author Ashvin
 * @since 2026-07-08
 * Description: 
 */
public class BranchDAO 
{
public BranchDAO()
{
}
public void add(Branch branch) throws DAOException
{
if(branch==null) throw new DAOException("branch required");
if(branch.getName()==null) throw new DAOException("branch name required");
String name=branch.getName();
if(name.isBlank()) throw new DAOException("branch name required");

Connection connection=DAOConnection.getConnection();
try
{
PreparedStatement preparedStatement;
ResultSet resultSet;
preparedStatement=connection.prepareStatement("select code from branch where name=?");
preparedStatement.setString(1,name);
resultSet=preparedStatement.executeQuery();
boolean valid=!resultSet.next();
resultSet.close();
preparedStatement.close();
if(!valid)
{
throw new DAOException("semester name: "+name+" already exists");
}

preparedStatement=connection.prepareStatement("insert into branch (name) values(?)",Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString(1,name);
preparedStatement.executeUpdate();
resultSet=preparedStatement.getGeneratedKeys();
resultSet.next();
Integer code=resultSet.getInt(1);
branch.setCode(code);
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
System.out.println(sqlException);       //later on add to log messages
throw new DAOException("Unable to add branch: "+name);
}
}
public void update(Branch branch) throws DAOException
{
if(branch==null) throw new DAOException("branch required");
int code=branch.getCode();
//if(code<=0) throw new DAOException("invalid branch code: "+code);
if(code<=0) throw new DAOException("invalid branch data provided, not found");
if(branch.getName()==null) throw new DAOException("branch name required");
String name=branch.getName();
if(name.isBlank()) throw new DAOException("branch name required");

Connection connection=DAOConnection.getConnection();
try
{
PreparedStatement preparedStatement;
ResultSet resultSet;
preparedStatement=connection.prepareStatement("select * from branch where code=?");
preparedStatement.setInt(1,code);
resultSet=preparedStatement.executeQuery();
boolean valid=resultSet.next();
resultSet.close();
preparedStatement.close();
if(!valid)
{
//throw new DAOException("invalid branch code: "+code);
throw new DAOException("invalid branch data provided, not found");
}
preparedStatement=connection.prepareStatement("select * from student_branch_mapping where branch_code=?");
preparedStatement.setInt(1,code);
resultSet=preparedStatement.executeQuery();
valid=!resultSet.next();
resultSet.close();
preparedStatement.close();
if(!valid)
{
throw new DAOException("unable to update record of branch, since respective branch is assigned to student data");
}

preparedStatement=connection.prepareStatement("update branch set name=? where code=?");
preparedStatement.setString(1,name);
preparedStatement.setInt(2,code);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
System.out.println(sqlException);       //later on add to log messages
throw new DAOException("Unable to update branch");
}
}
public void delete(int code) throws DAOException
{
if(code<=0) throw new DAOException("invalid branch data provided, not found");
Connection connection=DAOConnection.getConnection();
try
{
PreparedStatement preparedStatement;
ResultSet resultSet;
preparedStatement=connection.prepareStatement("select code from branch where code=?");
preparedStatement.setInt(1,code);
resultSet=preparedStatement.executeQuery();
boolean valid=resultSet.next();
resultSet.close();
preparedStatement.close();
if(!valid)
{
throw new DAOException("invalid branch data provided, not found");
}
preparedStatement=connection.prepareStatement("select * from student_branch_mapping where branch_code=?");
preparedStatement.setInt(1,code);
resultSet=preparedStatement.executeQuery();
valid=!resultSet.next();
resultSet.close();
preparedStatement.close();
if(!valid)
{
throw new DAOException("unable to delete record of branch, since respective branch is assigned to student data");
}
preparedStatement=connection.prepareStatement("delete from branch where code=?");
preparedStatement.setInt(1,code);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
System.out.println(sqlException);
throw new DAOException("unable to delete record of branch");
}
}
public List<Branch> getBranches() throws DAOException
{
List<Branch> branches=new ArrayList<>();
Branch branch;
int code;
String name;
Connection connection=DAOConnection.getConnection();
try
{
PreparedStatement preparedStatement=connection.prepareStatement("select * from branch order by name");
ResultSet resultSet=preparedStatement.executeQuery();
while(resultSet.next())
{
code=resultSet.getInt("code");
name=resultSet.getString("name").trim();
branch=new Branch();
branch.setCode(code);
branch.setName(name);
branches.add(branch);
}
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
System.out.println(sqlException);
throw new DAOException("unable to get branch record");
}
return branches;
}
public Branch getBranchByCode(int code) throws DAOException
{
Branch branch=null;
String name;
Connection connection=DAOConnection.getConnection();
try
{
PreparedStatement preparedStatement=connection.prepareStatement("select name from branch where code=?");
preparedStatement.setInt(1,code);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
name=resultSet.getString("name").trim();
branch=new Branch();
branch.setCode(code);
branch.setName(name);
}
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
System.out.println(sqlException);
throw new DAOException("unable to get branch record");
}
return branch;
}

}
