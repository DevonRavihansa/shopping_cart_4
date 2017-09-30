package com.example.demo.config;

import com.example.demo.domain.Order;
import com.example.demo.domain.OrderItem;
import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.cart.Cart;
import com.example.demo.dto.cart.CartItem;
import com.example.demo.dto.cart.CartService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Devon Ravihansa on 9/27/2017.
 */
@Component
public class AuthenticationSuccess implements AuthenticationSuccessHandler {

    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User user = userService.getUser(authentication.getName());

        if(cartService.hasCart(request.getSession())){
            Cart cart = cartService.getCart(request.getSession());
            for (CartItem item : cart.getItems()) {
                orderService.updateItem(user, productService.getProduct(item.getProduct().getSku()), item.getQuantity());
            }
        }

        SavedRequest savedRequest = (SavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
        if(savedRequest != null){
            response.sendRedirect(savedRequest.getRedirectUrl());
        }else{
            response.sendRedirect("/");
        }
    }
}
