package com.online.store.ecom.beans;

import java.math.*;

/**
 * @author Ashvin
 * @since 2026-08-07
 * Description: 
 */

public class ProductBean implements java.io.Serializable
{
private Long code;
private String name;
private BigDecimal price;
private Boolean isAvailable;
public ProductBean()
{
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

