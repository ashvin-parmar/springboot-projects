package com.ashvin.msgboard.dto;

public class Administrator implements java.io.Serializable
{
private String username;
private String password;
private String passwordKey;
public Administrator()
{
//do nothing
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
public void setPasswordKey(java.lang.String passwordKey)
{
this.passwordKey=passwordKey;
}
public java.lang.String getPasswordKey()
{
return this.passwordKey;
}
}

