package com.example.demo.controller.rest;

import com.example.demo.domain.Product;
import com.example.demo.dto.ProductDto;
import com.example.demo.service.ProductService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Devon Ravihansa on 9/28/2017.
 */
@RestController
@RequestMapping("/rest/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<ProductDto> getProducts(){
        return createDtos(productService.getAllProducts());
    }

    @RequestMapping("/{sku}")
    public ProductDto getProduct(@PathVariable("sku")String sku){
        return createDto(productService.getProduct(sku));
    }

    private ProductDto createDto(Product product){
        return modelMapper.map(product, ProductDto.class);
    }

    private List<ProductDto> createDtos(List<Product> products){
        return modelMapper.map(products, new TypeToken<List<ProductDto>>() {}.getType());
    }
}
