package com.ashvin.parmar.learn.jpacrud.webcontroller;
/**
 * @author Ashvin
 * @since 2026-09-12
 * Description: 
 */
import com.ashvin.parmar.learn.jpacrud.bean.*;
import com.ashvin.parmar.learn.jpacrud.bl.*;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;

@RestController
public class StudentController 
{
    private StudentBL studentBL;
    @Autowired
        public StudentController(StudentBL studentBL)
        {
            this.studentBL=studentBL;
        }
    @PostMapping("/addStudent")
        public StudentBean addStudent(StudentBean studentBean)
        {
            if(studentBean==null) return null;      //more web related later on
            String firstName=studentBean.getFirstName();
            if(firstName==null || firstName.length()==0) return null;       // ResponseEntity later on
            String lastName=studentBean.getLastName();
            if(lastName==null || lastName.length()==0) return null;
            String email=studentBean.getEmail();
            if(email==null || email.length()==0) return null;
            studentBL.createStudent(studentBean);
            System.out.println("Student has been created at web/pl(presentation layer) side with id: "+studentBean.getId());
            return studentBean;
        }
}

