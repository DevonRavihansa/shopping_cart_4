package com.example.demo.dto.cart;

import com.example.demo.dto.ProductDto;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Devon Ravihansa on 9/26/2017.
 */
public class CartItem {

    @Getter private final ProductDto product;

    @Getter @Setter private int quantity;

    private double subTotal;

    public CartItem(ProductDto product) {
        this.product = product;
        this.quantity = 1;
        this.subTotal = product.getPrice();
    }

    public CartItem(ProductDto product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.subTotal = product.getPrice() * quantity;
    }

    public double getSubTotal() {
        subTotal = product.getPrice() * quantity;
        return subTotal;
    }
}
