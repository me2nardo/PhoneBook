package com.lardi.dao;

import com.lardi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by leo on 16.04.2016.
 */
public interface ProductDao extends JpaRepository<Product,Integer> {
}
