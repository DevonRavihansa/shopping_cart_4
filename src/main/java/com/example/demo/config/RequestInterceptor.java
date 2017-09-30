package com.example.demo.config;

import com.example.demo.domain.Order;
import com.example.demo.domain.OrderItem;
import com.example.demo.domain.User;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.cart.Cart;
import com.example.demo.dto.cart.CartService;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

/**
 * Created by Devon Ravihansa on 9/27/2017.
 */
@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav) throws Exception {
        Principal userPrincipal = request.getUserPrincipal();
        if(userPrincipal != null){
            User user = userService.getUser(userPrincipal.getName());
            if(orderService.hasCartOrder(user)){
                Order order = orderService.getCartOrder(user);
                mav.addObject("cart", createCart(order));
            }
        }else{
            if(cartService.hasCart(request.getSession())){
                mav.addObject("cart", cartService.getCart(request.getSession()));
            }
        }
    }

    private Cart createCart(Order order){
        Cart cart = new Cart();
        for (OrderItem item : order.getOrderItems()) {
            cart.addItem(modelMapper.map(item.getProduct(), ProductDto.class), item.getQuantity());
        }
        return cart;
    }
}
