package com.example.demo.service;

import com.example.demo.domain.Product;

import java.util.List;

/**
 * Created by Devon Ravihansa on 9/25/2017.
 */
public interface ProductService {
    List<Product> getAllProducts();
    Product getProduct(String sku);
}
