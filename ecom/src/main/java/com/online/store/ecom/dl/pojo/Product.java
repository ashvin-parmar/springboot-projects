package com.online.store.ecom.dl.pojo;

import jakarta.persistence.*;
import java.math.*;

/**
 * @author Ashvin
 * @since 2026-08-07
 * Description: 
 */

@Entity(name="product")
public class Product
{
@Column(name="code")
@Id
//@GeneratedValue(strategy=GenerationType.AUTO)
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long code;
@Column(name="name",nullable=false,unique=true)
private String name;
@Column(name="price")
private BigDecimal price;
@Column(name="is_available")
private Boolean isAvailable;

public Product()
{
}
public Product(String name,BigDecimal price,Boolean isAvailable)
{
this.name=name;
this.price=price;
this.isAvailable=isAvailable;
}
public void setCode(Long code)
{
this.code=code;
}
public Long getCode()
{
return this.code;
}
public void setName(String name)
{
this.name=name;
}
public String getName()
{
return this.name;
}
public void setPrice(BigDecimal price)
{
this.price=price;
}
public BigDecimal getPrice()
{
return this.price;
}
public void setIsAvailable(Boolean isAvailable)
{
this.isAvailable=isAvailable;
}
public Boolean getIsAvailable()
{
return this.isAvailable;
}
}

