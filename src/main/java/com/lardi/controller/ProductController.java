package com.lardi.controller;

import com.lardi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by leo on 16.04.2016.
 */
@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public String getAllProduct(ModelMap model){
      model.addAttribute("products",productService.getProducts());
      return "products";
    }
}
