package com.ashvin.msgboard.beans;

/**
 * @author Ashvin
 * @since 2026-07-10
 * Description: 
 */
public class StudentViewBean
{
private String firstName;
private String lastName;
private String emailID;
private String rollNumber;
private SemesterBean semester;
private BranchBean branch;
public StudentViewBean()
{
this.firstName="";
this.lastName="";
this.emailID="";
this.rollNumber="";
this.semester=null;
this.branch=null;
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
public void setSemester(SemesterBean semester)
{
this.semester=semester;
}
public SemesterBean getSemester()
{
return this.semester;
}
public void setBranch(BranchBean branch)
{
this.branch=branch;
}
public BranchBean getBranch()
{
return this.branch;
}
}

