package com.online.store.ecom.dl.pojo;

/**
 * @author Ashvin
 * @since 2026-08-22
 * Description: 
 */
import jakarta.persistence.*;

@Embeddable
public class PurchaseOrderItemKey implements java.io.Serializable
{
@Column(name="product_code")
private Long productCode;
@Column(name="order_id")
private Long orderID;
public PurchaseOrderItemKey()
{
}
public PurchaseOrderItemKey(Long orderID,Long productCode)
{
this.orderID=orderID;
this.productCode=productCode;
}
public void setProductCode(Long productCode)
{
this.productCode=productCode;
}
public Long getProductCode()
{
return this.productCode;
}
public void setOrderID(Long orderID)
{
this.orderID=orderID;
}
public Long getOrderID()
{
return this.orderID;
}
}

