package com.example.demo.controller.rest;

import com.example.demo.domain.Order;
import com.example.demo.domain.OrderItem;
import com.example.demo.domain.Product;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.cart.Cart;
import com.example.demo.dto.cart.CartService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by Devon Ravihansa on 9/26/2017.
 */
@RestController
@RequestMapping("/rest/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Cart getCart(HttpSession session, Authentication authentication){
        if(authentication != null){
            Order order = orderService.getCartOrder(userService.getUser(authentication.getName()));
            return createCart(order);
        }
        return cartService.getCart(session);
    }

    @PostMapping
    public Cart addItem(HttpSession session,
                        Authentication authentication,
                        @RequestParam("sku")String sku,
                        @RequestParam(value = "quantity", required = false, defaultValue = "1")int quantity){
        Product product = productService.getProduct(sku);

        if(authentication != null){
            Order order = orderService.addItem(userService.getUser(authentication.getName()), product, quantity);
            Order order2 = orderService.getOrder(order.getPoNumber());
            System.out.println(order2.getPoNumber());
            for (OrderItem item : order2.getOrderItems()) {
                System.out.println(item.getId());
            }
            return createCart(order);
        }
        return cartService.getCart(session).addItem(modelMapper.map(product, ProductDto.class), quantity);
    }

    @PutMapping
    public Cart updateItem(HttpSession session,
                           Authentication authentication,
                           @RequestParam("sku")String sku,
                           @RequestParam("quantity")int quantity){
        Product product = productService.getProduct(sku);

        if(authentication != null){
            Order order = orderService.updateItem(userService.getUser(authentication.getName()), product, quantity);
            return createCart(order);
        }
        return cartService.getCart(session).updateItem(modelMapper.map(product, ProductDto.class), quantity);
    }

    @DeleteMapping
    public Cart removeItem(HttpSession session,
                           Authentication authentication,
                           @RequestParam("sku")String sku){
        Product product = productService.getProduct(sku);

        if(authentication != null){
            Order order = orderService.removeItem(userService.getUser(authentication.getName()), product);
            return createCart(order);
        }
        return cartService.getCart(session).removeItem(modelMapper.map(product, ProductDto.class));
    }

    private Cart createCart(Order order){
        Cart cart = new Cart();
        for (OrderItem item : order.getOrderItems()) {
            cart.addItem(modelMapper.map(item.getProduct(), ProductDto.class), item.getQuantity());
        }
        return cart;
    }
}
