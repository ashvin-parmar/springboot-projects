package com.online.store.ecom.dl.pojo;

import jakarta.persistence.*;
import java.math.*;

/**
 * @author Ashvin
 * @since 2026-08-07
 * Description: 
 */
@Entity
public class Customer 
{
@Column(name="code")
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long code;
@Column(name="name")
private String name;
@Column(name="email_id",nullable=false,unique=true)
private String emailID;
@Column(name="password",nullable=false)
private String password;
@Column(name="password_key",nullable=false)
private String passwordKey;
@Column(name="date_of_registration")
private java.sql.Date dateOfRegistration;
public Customer()
{
}
public Customer(String name,String emailID,String password,String passwordKey)
{
this.name=name;
this.emailID=emailID;
this.password=password;
this.passwordKey=passwordKey;
this.dateOfRegistration=dateOfRegistration;
}
public void setCode(java.lang.Long code)
{
this.code=code;
}
public java.lang.Long getCode()
{
return this.code;
}
public void setName(java.lang.String name)
{
this.name=name;
}
public java.lang.String getName()
{
return this.name;
}
public void setEmailID(java.lang.String emailID)
{
this.emailID=emailID;
}
public java.lang.String getEmailID()
{
return this.emailID;
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
public void setDateOfRegistration(java.sql.Date dateOfRegistration)
{
this.dateOfRegistration=dateOfRegistration;
}
public java.sql.Date getDateOfRegistration()
{
return this.dateOfRegistration;
}

}

