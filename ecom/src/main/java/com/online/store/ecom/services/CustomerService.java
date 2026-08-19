package com.online.store.ecom.services;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.online.store.ecom.dl.pojo.*;
import com.online.store.ecom.beans.*;
import com.online.store.ecom.dl.*;

/**
 * @author Ashvin
 * @since 2026-08-07
 * Description: 
 */
@RestController
public class CustomerService 
{
@Autowired
private CustomerRepository customerRepository;

@PostMapping("/addCustomer")
public CustomerBean addCustomer(CustomerBean customerBean)
{
Customer customer=new Customer(customerBean.getName(),customerBean.getEmailID(),customerBean.getPassword(),customerBean.getPassword());
customerRepository.save(customer);
customerBean.setCode(customer.getCode());
return customerBean;
}
    
}

