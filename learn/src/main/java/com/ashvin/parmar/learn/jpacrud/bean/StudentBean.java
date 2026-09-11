package com.ashvin.parmar.learn.jpacrud.bean;

/**
 * @author Ashvin
 * @since 2026-09-12
 * Description: 
 */

public class StudentBean implements java.io.Serializable
{
        private int id;
        private String firstName;
        private String lastName;
        private String email;
    public StudentBean()
    {
    }
    public void setId(int id)
    {
        this.id=id;
    }
    public int getId()
    {
        return this.id;
    }
    public void setFirstName(String firstName)
    {
        this.firstName=firstName;
    }
    public String getFirstName()
    {
        return this.firstName;
    }
    public void setLastName(String lastName)
    {
        this.lastName=lastName;
    }
    public String getLastName()
    {
        return this.lastName;
    }
    public void setEmail(String email)
    {
        this.email=email;
    }
    public String getEmail()
    {
        return this.email;
    }
}

