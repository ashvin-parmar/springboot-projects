package com.online.store.ecom.beans;

import java.math.*;
import java.util.*;
import org.springframework.format.annotation.*;
/**
 * @author Ashvin
 * @since 2026-08-07
 * Description: 
 */
public class CustomerBean implements java.io.Serializable
{
private Long code;
private String name;
private String emailID;
private String password;
@DateTimeFormat(pattern="yyyy-MM-dd")
private java.util.Date dateOfRegistration;
public CustomerBean()
{
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
public void setDateOfRegistration(java.util.Date dateOfRegistration)
{
this.dateOfRegistration=dateOfRegistration;
}
public java.util.Date getDateOfRegistration()
{
return this.dateOfRegistration;
}

}

