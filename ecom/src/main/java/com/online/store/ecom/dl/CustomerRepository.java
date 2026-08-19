package com.online.store.ecom.dl;

import org.springframework.data.repository.*;
import com.online.store.ecom.dl.pojo.*;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ashvin
 * @since 2026-08-07
 * Description: 
 */
public interface CustomerRepository extends CrudRepository<Customer,Long>
{
public Customer getByEmailID(String emailID);
}

