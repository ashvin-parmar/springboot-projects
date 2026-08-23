package com.online.store.ecom.beans;
/**
 * @author Ashvin
 * @since 2026-08-19
 * Description: 
 */
import java.math.*;
import java.util.*;
import org.springframework.format.annotation.*;

public class PurchaseOrderBean implements java.io.Serializable
{
private Long id;
private CustomerBean customer;
private Long customerCode;
@DateTimeFormat(pattern="yyyy-MM-dd")
private java.util.Date orderDate;
private BigDecimal totalAmount;
List<PurchaseOrderItemBean> purchaseOrderItems;
public PurchaseOrderBean()
{
}
public void setId(Long id)
{
this.id=id;
}
public Long getId()
{
return this.id;
}
public void setCustomer(CustomerBean customer)
{
this.customer=customer;
}
public CustomerBean getCustomer()
{
return this.customer;
}
public void setCustomerCode(Long customerCode)
{
this.customerCode=customerCode;
}
public Long getCustomerCode()
{
return this.customerCode;
}
public void setOrderDate(java.util.Date orderDate)
{
this.orderDate=orderDate;
}
public java.util.Date getOrderDate()
{
return this.orderDate;
}
public void setTotalAmount(BigDecimal totalAmount)
{
this.totalAmount=totalAmount;
}
public BigDecimal getTotalAmount()
{
return this.totalAmount;
}
public void setPurchaseOrderItems(List<PurchaseOrderItemBean> purchaseOrderItems)
{
this.purchaseOrderItems=purchaseOrderItems;
}
public List<PurchaseOrderItemBean> getPurchaseOrderItems()
{
return this.purchaseOrderItems;
}
}

