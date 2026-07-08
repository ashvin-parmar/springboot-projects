package com.ashvin.msgboard.beans;

public class SemesterBean implements java.io.Serializable
{
private int code;
private String name;
public SemesterBean()
{
this.code=0;
this.name="";
}
public void setCode(int code)
{
this.code=code;
}
public int getCode()
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
}
