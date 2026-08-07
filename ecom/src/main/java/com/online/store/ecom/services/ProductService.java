package com.online.store.ecom.services;

import com.online.store.ecom.dl.*;
import com.online.store.ecom.dl.pojo.*;
import com.online.store.ecom.beans.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;


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
    @GetMapping("/addProduct")
    public ProductBean addProduct(ProductBean productBean)
    {
        Product product=new Product(productBean.getName(),productBean.getPrice(),productBean.getIsAvailable());
        productRepository.save(product);
        productBean.setCode(product.getCode());
        return productBean;    
    }
}

