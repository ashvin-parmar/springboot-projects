package com.ashvin.msgboard.dto;

public class Branch
{
private int code;
private String name;
public Branch()
{
//do nothing
}
public Branch(int code,String name)
{
this.code=code;
this.name=name;
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
