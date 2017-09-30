package com.example.demo.dto.cart;


import com.example.demo.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Devon Ravihansa on 9/26/2017.
 */
public class Cart {
    private final List<CartItem> items;
    private int itemCount;
    private double total;

    public Cart() {
        this.items = new ArrayList<>();
        this.itemCount = 0;
        this.total = 0.0;
    }

    public int getItemCount() {
        itemCount = 0;
        for (CartItem item : items) {
            itemCount += item.getQuantity();
        }
        return itemCount;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public CartItem getItem(ProductDto product){
        for (CartItem item : items) {
            if(item.getProduct().getSku().equals(product.getSku())){
                return item;
            }
        }
        return null;
    }

    public double getTotal() {
        total = 0;
        for (CartItem item : items) {
            total += item.getSubTotal();
        }
        return total;
    }

    public Cart addItem(ProductDto product, int quantity){
        CartItem item = getItem(product);

        if(item != null){
            item.setQuantity(item.getQuantity() + quantity);
        }else{
            item = new CartItem(product, quantity);
            items.add(item);
        }
        return this;
    }

    public Cart updateItem(ProductDto product, int quantity){
        CartItem item = getItem(product);

        if(item != null){
            item.setQuantity(quantity);
        }else{
            // throw new ProductNotFoundException();
        }
        return this;
    }

    public Cart removeItem(ProductDto product){
        CartItem item = getItem(product);

        if(item != null){
            items.remove(item);
        }else{
            // throw new ProductNotFoundException();
        }
        return this;
    }

    public void clear(){
        items.clear();
        itemCount = 0;
        total = 0;
    }
}
