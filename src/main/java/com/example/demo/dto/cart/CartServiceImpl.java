package com.example.demo.dto.cart;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Created by Devon Ravihansa on 9/26/2017.
 */
@Service
public class CartServiceImpl implements CartService {

    private static final String SESSION_KEY_CART = "cart";

    @Override
    public Cart getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute(SESSION_KEY_CART);

        if(cart == null){
            cart = new Cart();
            setCart(session, cart);
        }

        return cart;
    }

    @Override
    public void setCart(HttpSession session, Cart cart) {
        session.setAttribute(SESSION_KEY_CART, cart);
    }

    @Override
    public void removeCart(HttpSession session) {
        session.removeAttribute(SESSION_KEY_CART);
    }

    @Override
    public boolean hasCart(HttpSession session) {
        return session.getAttribute(SESSION_KEY_CART) != null ? true : false;
    }
}
