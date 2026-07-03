package com.ashvin.msgboard.beans;
/**
 * @author Ashvin
 * @since 2026-07-03
 * Description: 
 */
public class DatabaseBean implements java.io.Serializable
{
private String driver;
private String connectionString;
private String username;
private String password;
public DatabaseBean()
{
this.driver=null;
this.connectionString=null;
this.username=null;
this.password=null;
}
public void setDriver(java.lang.String driver)
{
this.driver=driver;
}
public java.lang.String getDriver()
{
return this.driver;
}
public void setConnectionString(java.lang.String connectionString)
{
this.connectionString=connectionString;
}
public java.lang.String getConnectionString()
{
return this.connectionString;
}
public void setUsername(java.lang.String username)
{
this.username=username;
}
public java.lang.String getUsername()
{
return this.username;
}
public void setPassword(java.lang.String password)
{
this.password=password;
}
public java.lang.String getPassword()
{
return this.password;
}
}
