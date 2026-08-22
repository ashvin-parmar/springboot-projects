package com.online.store.ecom.dl.pojo;
/**
 * @author Ashvin
 * @since 2026-08-19
 * Description: 
 */
import jakarta.persistence.*;
import java.math.*;

@Entity(name="purchase_order")
public class PurchaseOrder implements java.io.Serializable
{
@Id
@Column(name="id")
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;
@ManyToOne()
@JoinColumn(name="customer_code",referencedColumnName="code")
private Customer customer;
@Column(name="order_date")
private java.sql.Date orderDate;
@Column(name="total_amount")
private BigDecimal totalAmount;

public PurchaseOrder()
{
}
public PurchaseOrder(Customer customer,java.sql.Date orderDate,BigDecimal totalAmount)
{
this.customer=customer;
this.orderDate=orderDate;
this.totalAmount=totalAmount;
}
public void setCustomer(Customer customer)
{
this.customer=customer;
}
public Customer getCustomer()
{
return this.customer;
}
public void setOrderDate(java.sql.Date orderDate)
{
this.orderDate=orderDate;
}
public java.sql.Date getOrderDate()
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
}

