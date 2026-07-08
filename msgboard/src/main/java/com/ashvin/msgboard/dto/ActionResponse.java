package com.ashvin.msgboard.dto;
/**
 * @author Ashvin
 * @since 2026-07-08
 * Description: 
 */
public class ActionResponse 
{
private boolean success;
private String exception;
private Object result;
public ActionResponse()
{
//do nothing
}
public void setSuccess(boolean success)
{
this.success=success;
}
public boolean getSuccess()
{
return this.success;
}
public void setException(java.lang.String exception)
{
this.exception=exception;
}
public java.lang.String getException()
{
return this.exception;
}
public void setResult(java.lang.Object result)
{
this.result=result;
}
public java.lang.Object getResult()
{
return this.result;
}
}

