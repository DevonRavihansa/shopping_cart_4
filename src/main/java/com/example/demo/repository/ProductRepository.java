package com.example.demo.repository;

import com.example.demo.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Devon Ravihansa on 9/25/2017.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findBySku(String sku);
}
