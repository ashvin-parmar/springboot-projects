package com.ashvin.msgboard.beans;

import java.util.*;

/**
 * @author Ashvin
 * @since 2026-07-08
 * Description: 
 */
public class MessageBoardBean implements java.io.Serializable
{
private List<StudentViewBean> students;
private List<BranchBean> branches;
private List<SemesterBean> semesters;
public MessageBoardBean()
{
this.students=new ArrayList<>();
this.branches=new ArrayList<>();
this.semesters=new ArrayList<>();
}
public void setStudents(List<StudentViewBean> students)
{
this.students=students;
}
public void setBranches(List<BranchBean> branches)
{
this.branches=branches;
}
public void setSemesters(List<SemesterBean> semesters)
{
this.semesters=semesters;
}
public List<StudentViewBean> getStudents()
{
return this.students;
}
public List<BranchBean> getBranches()
{
return this.branches;
}
public List<SemesterBean> getSemesters()
{
return this.semesters;
}
}
