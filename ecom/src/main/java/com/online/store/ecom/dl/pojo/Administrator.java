package com.online.store.ecom.dl.pojo;

import jakarta.persistence.*;

/**
 * @author Ashvin
 * @since 2026-08-07
 * Description: 
 */
@Entity
@Table(name="administrator")
public class Administrator 
{
@Id
@Column(name="username",nullable=false)
private String username;
@Column(name="password",nullable=false)
private String password;
@Column(name="password_key",nullable=false)
private String passwordKey;
public Administrator()
{
}
public Administrator(String username,String password,String passwordKey)
{
this.username=username;
this.password=password;
this.passwordKey=passwordKey;
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
public void setPasswordKey(String passwordKey)
{
this.passwordKey=passwordKey;
}
public String getPasswordKey()
{
return this.passwordKey;
}
}

