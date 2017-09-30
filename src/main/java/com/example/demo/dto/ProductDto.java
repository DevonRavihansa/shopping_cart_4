package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Devon Ravihansa on 9/26/2017.
 */
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ProductDto {

    private String sku;
    private String name;
    private double price;
    private String image;
}
