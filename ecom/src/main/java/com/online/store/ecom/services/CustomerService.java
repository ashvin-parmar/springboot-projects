package com.online.store.ecom.services;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.online.store.ecom.dl.pojo.*;
import com.online.store.ecom.beans.*;
import com.online.store.ecom.dl.*;

import java.util.*;
import java.math.*;
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

@PostMapping("/customer/add")
public CustomerBean addCustomer(CustomerBean customerBean)
{
java.util.Date utilDate=customerBean.getDateOfRegistration();
java.sql.Date sqlDate;
if(utilDate!=null)
{
    sqlDate=new java.sql.Date(utilDate.getTime());
}
else
{
    utilDate=new java.util.Date();
    sqlDate=new java.sql.Date(utilDate.getTime());
}
String passwordKey=java.util.UUID.randomUUID().toString().replaceAll("-","d");
Customer customer=new Customer(customerBean.getName(),customerBean.getEmailID(),customerBean.getPassword(),passwordKey,sqlDate);
customerRepository.save(customer);
customerBean.setCode(customer.getCode());
customerBean.setPassword(null);
customerBean.setDateOfRegistration(utilDate);
return customerBean;
}
@PostMapping("/customer/update")
public CustomerBean updateCustomer(CustomerBean customerBean)
{
Long code=customerBean.getCode();
Optional<Customer> customerResult=customerRepository.findById(code);
Customer customer=null;
if(customerResult.isPresent()) customer=customerResult.get();
customer.setName(customerBean.getName());
customerRepository.save(customer);
customerBean.setPassword(null);
customerBean.setDateOfRegistration(new java.util.Date(customer.getDateOfRegistration().getTime()));
return customerBean;
}

@GetMapping("/customer/getByEmailID")
public CustomerBean getByEmailID(@RequestParam("emailID")String emailID)
{
Optional<Customer> customerResult=customerRepository.findByEmailID(emailID);
Customer customer=null;
if(customerResult.isPresent()) customer=customerResult.get();
if(customer==null) return null;
CustomerBean customerBean=new CustomerBean();
customerBean.setCode(customer.getCode());
customerBean.setName(customer.getName());
customerBean.setEmailID(customer.getEmailID());
customerBean.setDateOfRegistration(new java.util.Date(customer.getDateOfRegistration().getTime()));
return customerBean;
}

@GetMapping("/customer/get/{code}")
public CustomerBean getByEmailID(@PathVariable("code")Long code)
{
Optional<Customer> customerResult=customerRepository.findById(code);
Customer customer=null;
if(customerResult.isPresent()) customer=customerResult.get();
if(customer==null) return null;
CustomerBean customerBean=new CustomerBean();
customerBean.setCode(customer.getCode());
customerBean.setName(customer.getName());
customerBean.setEmailID(customer.getEmailID());
customerBean.setDateOfRegistration(new java.util.Date(customer.getDateOfRegistration().getTime()));
return customerBean;
}

@GetMapping("/customer/getAll")
public List<CustomerBean> getAll()
{
List<CustomerBean> customerBeans=new ArrayList<>();
customerRepository.findAll().forEach(customer->{
CustomerBean customerBean=new CustomerBean();
customerBean.setName(customer.getName());
customerBean.setCode(customer.getCode());
customerBean.setEmailID(customer.getEmailID());
customerBean.setDateOfRegistration(new java.util.Date(customer.getDateOfRegistration().getTime()));
customerBeans.add(customerBean);
});
return customerBeans;
}

@PostMapping("/customer/delete/{code}")
public Boolean deleteByCode(@PathVariable("code")Long code)
{
if(!isCustomerCodeExists(code)) return false;
customerRepository.deleteById(code);
return true;
}

@GetMapping("/customer/exists/{code}")
public Boolean isCustomerCodeExists(@PathVariable("code")Long code)
{
return customerRepository.existsById(code);
}
    
}

