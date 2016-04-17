package com.lardi.service;

import com.lardi.model.Product;

import java.util.List;

/**
 * Created by leo on 16.04.2016.
 */
public interface ProductService {
     void addProduct(Product product);
     List<Product> getProducts();

}
