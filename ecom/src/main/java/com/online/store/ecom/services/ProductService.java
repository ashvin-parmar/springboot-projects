package com.online.store.ecom.services;

import com.online.store.ecom.dl.*;
import com.online.store.ecom.dl.pojo.*;
import com.online.store.ecom.beans.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;

import java.util.*;
import java.math.*;

/**
 * @author Ashvin
 * @since 2026-08-07
 * Description: 
 */
@Controller
public class ProductService 
{
@Autowired
private ProductRepository productRepository;

    @ResponseBody
    @PostMapping("/product/add")
    public ProductBean addProduct(ProductBean productBean)
    {
        Product product=new Product(productBean.getName(),productBean.getPrice(),productBean.getIsAvailable());
        productRepository.save(product);
        productBean.setCode(product.getCode());
        return productBean;    
    }
@ResponseBody
@PostMapping("/product/update")
public ProductBean updateProduct(ProductBean productBean)
{
Product product=new Product(productBean.getName(),productBean.getPrice(),productBean.getIsAvailable());
product.setCode(productBean.getCode());
productRepository.save(product);
return productBean;
}
@ResponseBody
@PostMapping("/product/delete")
public Boolean deleteProduct(@RequestParam("code")Long code)
{
if(!exists(code)) return false;
productRepository.deleteById(code);
return true;
}
@ResponseBody
@GetMapping("/product/getAll")
public List<Product> getAll()
{
// List<ProductBean> productBeans=new ArrayList<>();
// ProductBean productBean;
// productRepository.findAll()
// .forEach((p)=>{
// productBean=new ProductBean();           //JSON data passed, same for ProductBean and Product
// productBean.setName(product.getName());
// productBean.setCode(product.getCode());
// productBean.setPrice(product.getPrice());
// productBean.setIsAvailable(product.getIsAvailable());
// productBeans.add(productBean);
// });
// return productBeans; //for List<ProductBean>

List<Product> products=new ArrayList<>();
productRepository.findAll().forEach(products::add);
return products;
}
@ResponseBody
@GetMapping("/product/get/{code}")
public Product getByCode(@PathVariable("code")Long code)
{
Optional<Product> productResult=productRepository.findById(code);
Product product=null;
if(productResult.isPresent()) product=productResult.get();
// ProductBean productBean=new ProductBean();           //JSON data passed, same for ProductBean and Product
// productBean.setName(product.getName());
// productBean.setCode(product.getCode());
// productBean.setPrice(product.getPrice());
// productBean.setIsAvailable(product.getIsAvailable());
// return productBean;
return product;
}
@ResponseBody
@GetMapping("/product/get")
public Product getByName(@RequestParam("name")String name)
{
Optional<Product> productResult=productRepository.findByName(name);
Product product=null;
if(productResult.isPresent()) product=productResult.get();
return product;
}
@ResponseBody
@GetMapping("/product/exists/{code}")
public Boolean exists(@PathVariable("code")Long code)
{
return productRepository.existsById(code);
}
@ResponseBody
@GetMapping("/product/count")
public Long getCount()
{
return productRepository.count();
}
}

