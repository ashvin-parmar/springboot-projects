package com.ashvin.parmar.learn.jpacrud.dao;

/**
 * @author Ashvin
 * @since 2026-09-12
 * Description: 
 */
import com.ashvin.parmar.learn.jpacrud.entity.*;
import jakarta.persistence.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.beans.factory.annotation.*;


@Repository
public class StudentDAO  implements StudentDAOInterface
{
private EntityManager entityManager;

@Autowired
public StudentDAO(EntityManager entityManager)
{
this.entityManager=entityManager;
}


@Override
@Transactional      // 
public void save(Student student)
{
entityManager.persist(student);
}
    
}

