package com.online.store.ecom.beans;

import org.springframework.format.annotation.*;
import java.math.*;

/**
 * @author Ashvin
 * @since 2026-08-07
 * Description: 
 */

public class FeedBackBean implements java.io.Serializable
{
private Long id;
@DateTimeFormat(pattern="yyyy-MM-dd")
private java.util.Date givenOn;
private Long productCode;
private Long customerCode;
private ProductBean product;
private CustomerBean customer;
private String feedback;

public FeedBackBean()
{
}
public void setId(java.lang.Long id)
{
this.id=id;
}
public java.lang.Long getId()
{
return this.id;
}
public void setGivenOn(java.util.Date givenOn)
{
this.givenOn=givenOn;
}
public java.util.Date getGivenOn()
{
return this.givenOn;
}
public void setProductCode(Long productCode)
{
this.productCode=productCode;
}
public Long getProductCode()
{
return this.productCode;
}
public void setCustomerCode(Long customerCode)
{
this.customerCode=customerCode;
}
public Long getCustomerCode()
{
return this.customerCode;
}
public void setProduct(ProductBean product)
{
this.product=product;
}
public ProductBean getProduct()
{
return this.product;
}
public void setCustomer(CustomerBean customer)
{
this.customer=customer;
}
public CustomerBean getCustomer()
{
return this.customer;
}
public void setFeedback(java.lang.String feedback)
{
this.feedback=feedback;
}
public java.lang.String getFeedback()
{
return this.feedback;
}
}

