package com.online.store.ecom.beans;

/**
 * @author Ashvin
 * @since 2026-08-22
 * Description: 
 */
import java.math.*;

public class PurchaseOrderItemBean implements java.io.Serializable
{
private Integer quantity;
private BigDecimal price;
private Long productCode;
private ProductBean product;
public PurchaseOrderItemBean()
{
}
public void setProductCode(Long productCode)
{
this.productCode=productCode;
}
public Long getProductCode()
{
return this.productCode;
}
public void setProduct(ProductBean product)
{
this.product=product;
}
public ProductBean getProduct()
{
return this.product;
}
public void setQuantity(Integer quantity)
{
this.quantity=quantity;
}
public Integer getQuantity()
{
return this.quantity;
}
public void setPrice(BigDecimal price)
{
this.price=price;
}
public BigDecimal getPrice()
{
return this.price;
}
}

