package com.online.store.ecom.beans;


/**
 * @author Ashvin
 * @since 2026-08-07
 * Description: 
 */
public class AdministratorBean  implements java.io.Serializable
{
private String username;
private String password;
public AdministratorBean()
{
}
public void setUsername(String username)
{
this.username=username;
}
public String getUsername()
{
return this.username;
}
public void setPassword(String password)
{
this.password=password;
}
public String getPassword()
{
return this.password;
}
}

