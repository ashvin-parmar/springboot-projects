package com.online.store.ecom.services;

/**
 * @author Ashvin
 * @since 2026-08-22
 * Description: 
 */
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.online.store.ecom.dl.pojo.*;
import com.online.store.ecom.dl.*;
import com.online.store.ecom.beans.*;
import java.util.*;
import java.math.*;

@Controller
public class PurchaseOrderService
{
@Autowired
ProductRepository productRepository;

@Autowired
CustomerRepository customerRepository;

@Autowired
PurchaseOrderItemRepository purchaseOrderItemRepository;

@Autowired
PurchaseOrderRepository purchaseOrderRepository;

@ResponseBody
@PostMapping("/purchaseOrder/add")
public PurchaseOrderBean add(@RequestBody PurchaseOrderBean purchaseOrderBean)
{
if(purchaseOrderBean==null)
{
System.out.println("null arrived");
return null;
}
java.util.Date utilOrderDate=purchaseOrderBean.getOrderDate();
Long customerCode=purchaseOrderBean.getCustomerCode();
BigDecimal totalAmount=purchaseOrderBean.getTotalAmount();
List<PurchaseOrderItemBean> purchaseOrderItemBeans=purchaseOrderBean.getPurchaseOrderItems();

java.sql.Date sqlOrderDate=new java.sql.Date(utilOrderDate.getTime());
PurchaseOrderItem purchaseOrderItem;
Product product;
Customer customer;
Optional<Customer> customerResult;
Optional<Product> productResult;
PurchaseOrderItemKey purchaseOrderItemKey;
PurchaseOrder purchaseOrder;

customerResult=customerRepository.findById(customerCode);
if(customerResult.isPresent()) customer=customerResult.get();
else return null;

List<PurchaseOrderItem> purchaseOrderItems=new ArrayList<PurchaseOrderItem>();
for(PurchaseOrderItemBean purchaseOrderItemBean:purchaseOrderItemBeans)
{
productResult=productRepository.findById(purchaseOrderItemBean.getProductCode());
if(productResult.isPresent()) product=productResult.get();
else return null;
purchaseOrderItemKey=new PurchaseOrderItemKey();
purchaseOrderItemKey.setProductCode(product.getCode());
purchaseOrderItem=new PurchaseOrderItem(purchaseOrderItemKey,purchaseOrderItemBean.getQuantity(),purchaseOrderItemBean.getPrice());
purchaseOrderItems.add(purchaseOrderItem);
}
purchaseOrder=new PurchaseOrder(customer,sqlOrderDate,totalAmount);
purchaseOrderRepository.save(purchaseOrder);
Long id=purchaseOrder.getId();

for(PurchaseOrderItem poi:purchaseOrderItems)
{
poi.getPurchaseOrderItemKey().setOrderID(id);
purchaseOrderItemRepository.save(poi);
}
purchaseOrderBean.setId(id);
return purchaseOrderBean;
}

@PostMapping("/purchaseOrder/update")
@ResponseBody
public PurchaseOrderBean update(@RequestBody PurchaseOrderBean purchaseOrderBean)
{
Long id=purchaseOrderBean.getId();
Long customerCode=purchaseOrderBean.getCustomerCode();
Optional<Customer> customerResult=customerRepository.findById(customerCode);
Customer customer;
if(customerResult.isPresent()) customer=customerResult.get();
else return null;
BigDecimal totalAmount=purchaseOrderBean.getTotalAmount();
java.sql.Date sqlOrderDate=new java.sql.Date(purchaseOrderBean.getOrderDate().getTime());
List<PurchaseOrderItemBean> purchaseOrderItemBeans=purchaseOrderBean.getPurchaseOrderItems();

List<PurchaseOrderItem> purchaseOrderItems=new ArrayList<>();
PurchaseOrderItem purchaseOrderItem;
PurchaseOrderItemKey purchaseOrderItemKey;

for(PurchaseOrderItemBean purchaseOrderItemBean:purchaseOrderItemBeans)
{
if(!productRepository.existsById(purchaseOrderItemBean.getProductCode())) return null;
purchaseOrderItemKey=new PurchaseOrderItemKey();
purchaseOrderItemKey.setProductCode(purchaseOrderItemBean.getProductCode());
purchaseOrderItemKey.setOrderID(id);
Optional<PurchaseOrderItem> purchaseOrderItemResult=purchaseOrderItemRepository.findById(purchaseOrderItemKey);
if(purchaseOrderItemResult.isPresent()) 
{
purchaseOrderItem=purchaseOrderItemResult.get();
purchaseOrderItem.setQuantity(purchaseOrderItemBean.getQuantity());
purchaseOrderItem.setPrice(purchaseOrderItemBean.getPrice());
purchaseOrderItemRepository.save(purchaseOrderItem);
}
else
{
purchaseOrderItem=new PurchaseOrderItem(purchaseOrderItemKey,purchaseOrderItemBean.getQuantity(),purchaseOrderItemBean.getPrice());
purchaseOrderItemRepository.save(purchaseOrderItem);
}
purchaseOrderItems.add(purchaseOrderItem);
}
PurchaseOrder purchaseOrder=new PurchaseOrder(customer,sqlOrderDate,totalAmount);
purchaseOrder.setId(id);
purchaseOrderRepository.save(purchaseOrder);
return purchaseOrderBean;
}


}

