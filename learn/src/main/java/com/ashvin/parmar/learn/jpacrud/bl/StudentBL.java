package com.ashvin.parmar.learn.jpacrud.bl;

/**
 * @author Ashvin
 * @since 2026-09-12
 * Description: 
 */
import com.ashvin.parmar.learn.jpacrud.bean.*;
import com.ashvin.parmar.learn.jpacrud.entity.*;
import com.ashvin.parmar.learn.jpacrud.dao.*;

import org.springframework.stereotype.*;
import org.springframework.context.annotation.*;
import org.springframework.beans.factory.annotation.*;

@Service
public class StudentBL 
{
    private StudentDAO studentDAO;
    @Autowired
        public StudentBL(StudentDAO studentDAO)
        {
            this.studentDAO=studentDAO;
        }

    public void createStudent(StudentBean studentBean)
    {
        System.out.println("Create student");
        Student student=new Student(studentBean.getFirstName(),studentBean.getLastName(),studentBean.getEmail());
        System.out.println("Saving the student");
        studentDAO.save(student);
        System.out.println("saved student geerated id: "+student.getId());
        studentBean.setId(student.getId());
    }
}

