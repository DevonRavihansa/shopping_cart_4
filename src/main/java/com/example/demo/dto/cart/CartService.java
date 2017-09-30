package com.example.demo.dto.cart;

import javax.servlet.http.HttpSession;

/**
 * Created by Devon Ravihansa on 9/26/2017.
 */
public interface CartService {
    Cart getCart(HttpSession session);
    void setCart(HttpSession session, Cart cart);
    void removeCart(HttpSession session);
    boolean hasCart(HttpSession session);
}
