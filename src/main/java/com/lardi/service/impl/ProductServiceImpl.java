package com.lardi.service.impl;

import com.lardi.dao.ProductDao;
import com.lardi.model.Product;
import com.lardi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by leo on 16.04.2016.
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public void addProduct(Product product) {
       productDao.save(product);
    }

    @Override
    public List<Product> getProducts() {
        return productDao.findAll();
    }
}
