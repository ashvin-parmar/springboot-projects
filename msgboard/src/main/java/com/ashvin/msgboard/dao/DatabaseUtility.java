package com.ashvin.msgboard.dao;

import java.sql.*;

public class DatabaseUtility
{
private DatabaseUtility(){};
public static void createTables() throws DAOException
{
Connection connection=DAOConnection.getConnection();
try
{
Statement statement;
String sqlStatement="";

sqlStatement="create table administrator";
sqlStatement+="(";
sqlStatement+="username char(15) not null primary key,";
sqlStatement+="password char(100) not null,";
sqlStatement+="password_key char(100) not null";
sqlStatement+=");";

statement=connection.createStatement();
statement.executeUpdate(sqlStatement);
statement.close();

sqlStatement="create table branch";
sqlStatement+="(";
sqlStatement+="code int primary key auto_increment,";
sqlStatement+="name char(50) not null unique";
sqlStatement+=");";

statement=connection.createStatement();
statement.executeUpdate(sqlStatement);
statement.close();

sqlStatement="create table semester";
sqlStatement+="(";
sqlStatement+="code int primary key auto_increment,";
sqlStatement+="name char(25) not null unique";
sqlStatement+=");";

statement=connection.createStatement();
statement.executeUpdate(sqlStatement);
statement.close();

sqlStatement="create table student";
sqlStatement+="(";
sqlStatement+="roll_number char(15) primary key,";
sqlStatement+="first_name char(20) not null,";
sqlStatement+="last_name char(20) not null,";
sqlStatement+="email_id char(100) not null unique,";
sqlStatement+="password char(100) not null,";
sqlStatement+="password_key char(100) not null";
sqlStatement+=");";

statement=connection.createStatement();
statement.executeUpdate(sqlStatement);
statement.close();

sqlStatement="create table student_branch_mapping";
sqlStatement+="(";
sqlStatement+="roll_number char(15) not null,";
sqlStatement+="branch_code int not null,";
sqlStatement+="primary key (roll_number,branch_code),";
sqlStatement+="foreign key (roll_number) references student(roll_number),";
sqlStatement+="foreign key (branch_code) references branch(code)";
sqlStatement+=");";

statement=connection.createStatement();
statement.executeUpdate(sqlStatement);
statement.close();

sqlStatement="create table student_semester_mapping";
sqlStatement+="(";
sqlStatement+="roll_number char(15) not null,";
sqlStatement+="semester_code int not null,";
sqlStatement+="primary key (roll_number,semester_code),";
sqlStatement+="foreign key (roll_number) references student(roll_number),";
sqlStatement+="foreign key (semester_code) references semester(code)";
sqlStatement+=");";

statement=connection.createStatement();
statement.executeUpdate(sqlStatement);
statement.close();
System.out.println("All tables created");
connection.close();
}catch(SQLException sqlException)
{
System.out.println(sqlException);
throw new DAOException("Unable to create tables");
}
}
}
