package com.online.store.ecom.services;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;

import com.online.store.ecom.dl.pojo.*;
import com.online.store.ecom.dl.*;
import com.online.store.ecom.beans.*;

/**
 * @author Ashvin
 * @since 2026-08-19
 * Description: 
 */
@Controller

public class FeedBackService 
{
@Autowired
CustomerRepository customerRepository;
@Autowired
ProductRepository productRepository;
@Autowired
FeedBackRepository feedBackRepository;

@PostMapping("/feedback/add")
@ResponseBody
public FeedBackBean add(FeedBackBean feedBackBean)
{
String feedBackMessage=feedBackBean.getFeedback();
Long feedBackId;
Long customerCode=feedBackBean.getCustomerCode();
Long productCode=feedBackBean.getProductCode();
java.util.Date utilGivenOn=feedBackBean.getGivenOn();
java.sql.Date sqlGivenOn=new java.sql.Date(utilGivenOn.getYear(),utilGivenOn.getMonth(),utilGivenOn.getDate());

Optional<Product> productResult=productRepository.findById(productCode);
Optional<Customer> customerResult=customerRepository.findById(customerCode);
Product product=null;
Customer customer=null;

if(productResult.isPresent()) product=productResult.get();
if(customerResult.isPresent()) customer=productResult.get(); 


FeedBack feedBack=new FeedBack(feedBackMessage,sqlGivenOn,customer,product);
feedBackRepository.save(feedBack);
feedBackId=feedBack.getId();

feedBackBean.setId(feedBackId);
return feedBackBean;
}


}

