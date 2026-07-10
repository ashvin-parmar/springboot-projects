package com.ashvin.msgboard.dto;

/**
 * @author Ashvin
 * @since 2026-07-10
 * Description: 
 */
public class StudentView 
{
private String firstName;
private String lastName;
private String emailID;
private String rollNumber;
private Semester semester;
private Branch branch;
public StudentView()
{
//do nothing
}
public StudentView(String firstName,String lastName,String rollNumber,String emailID,Branch branch,Semester semester)
{
this.firstName=firstName;
this.lastName=lastName;
this.rollNumber=rollNumber;
this.emailID=emailID;
this.branch=branch;
this.semester=semester;
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
public void setEmailID(java.lang.String emailID)
{
this.emailID=emailID;
}
public java.lang.String getEmailID()
{
return this.emailID;
}
public void setRollNumber(java.lang.String rollNumber)
{
this.rollNumber=rollNumber;
}
public java.lang.String getRollNumber()
{
return this.rollNumber;
}
public void setSemester(Semester semester)
{
this.semester=semester;
}
public Semester getSemester()
{
return this.semester;
}
public void setBranch(Branch branch)
{
this.branch=branch;
}
public Branch getBranch()
{
return this.branch;
}
}

