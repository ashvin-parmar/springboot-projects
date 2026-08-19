package com.online.store.ecom.dl.pojo;
/**
 * @author Ashvin
 * @since 2026-08-14
 * Description: 
 */
import jakarta.persistence.*;
import java.math.*;

@Entity(name="feed_back")
public class FeedBack 
{
@Id
@Column(name="id")
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;

@ManyToOne()
@JoinColumn(name="customer_code",referencedColumnName="code")
private Customer customer;

@ManyToOne()
@JoinColumn(name="product_code",referencedColumnName="code")
private Product product;

@Column(name="feed_back")
private String feedBack;

@Column(name="given_on")
private java.sql.Date givenOn;

public FeedBack()
{
}
public FeedBack(String feedBack,java.sql.Date givenOn,Customer customer,Product product)
{
this.customer=customer;
this.product=product;
this.feedBack=feedBack;
this.givenOn=givenOn;
}
public void setId(Long id)
{
this.id=id;
}
public Long getId()
{
return this.id;
}
public void setCustomer(Customer customer)
{
this.customer=customer;
}
public Customer getCustomer()
{
return this.customer;
}
public void setProduct(Product product)
{
this.product=product;
}
public Product getProduct()
{
return this.product;
}
public void setFeedBack(String feedBack)
{
this.feedBack=feedBack;
}
public String getFeedBack()
{
return this.feedBack;
}
public void setGivenOn(java.sql.Date givenOn)
{
this.givenOn=givenOn;
}
public java.sql.Date getGivenOn()
{
return this.givenOn;
}
}
