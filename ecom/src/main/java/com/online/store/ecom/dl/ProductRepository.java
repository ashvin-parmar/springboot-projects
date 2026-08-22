package com.online.store.ecom.dl;

import org.springframework.data.repository.*;
import com.online.store.ecom.dl.pojo.*;

/**
 * @author Ashvin
 * @since 2026-08-07
 * Description: 
 */
public interface ProductRepository extends CrudRepository<Product,Long>
{
public java.util.Optional<Product> findByName(String name);
}

