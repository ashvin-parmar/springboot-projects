package com.ashvin.parmar.learn.jpacrud.entity;

import jakarta.persistence.*;

/**
 * @author Ashvin
 * @since 2026-09-11
 * Description: 
 */
@Entity
@Table(name="student")
public class Student 
{
    @Id
        @Column(name="id")
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        private int id;
    @Column(name="first_name")
        private String firstName;
    @Column(name="last_name")
        private String lastName;
    @Column(name="email")
        private String email;

    public Student()
    {
    }
    public Student(String firstName,String lastName,String email)
    {
        //code is auto_increment, so only firstName, lastName, and email part is considered. 
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
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

    public String toString()
    {
        return "Student{"+
            "id="+id+
            ", firstName='"+firstName+'\''+
            ", lastName='"+lastName+'\''+
            ", email='"+email+'\''+
            "}";
    }
}

