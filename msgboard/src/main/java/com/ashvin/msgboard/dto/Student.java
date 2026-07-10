package com.ashvin.msgboard.dto;

/**
 * @author Ashvin
 * @since 2026-07-10
 * Description: 
 */
public class Student 
{
private String firstName;
private String lastName;
private String rollNumber;
private String emailID;
private String password;
private int semesterCode;
private int branchCode;
public Student()
{
//do nothing
}
public void setFirstName(java.lang.String firstName)
{
this.firstName=firstName;
}
public java.lang.String getFirstName()
{
return this.firstName;
}
public void setLastName(java.lang.String lastName)
{
this.lastName=lastName;
}
public java.lang.String getLastName()
{
return this.lastName;
}
public void setRollNumber(java.lang.String rollNumber)
{
this.rollNumber=rollNumber;
}
public java.lang.String getRollNumber()
{
return this.rollNumber;
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
public void setSemesterCode(int semesterCode)
{
this.semesterCode=semesterCode;
}
public int getSemesterCode()
{
return this.semesterCode;
}
public void setBranchCode(int branchCode)
{
this.branchCode=branchCode;
}
public int getBranchCode()
{
return this.branchCode;
}
}


