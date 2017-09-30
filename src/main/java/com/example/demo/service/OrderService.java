package com.example.demo.service;

import com.example.demo.domain.*;

import java.util.List;

/**
 * Created by Devon Ravihansa on 9/25/2017.
 */
public interface OrderService {

    List<Order> getAllOrders();
    List<Order> getAllOrders(User user);
    List<Order> getAllOrders(OrderStatus orderStatus);

    Order getOrder(String poNumber);
    Order getCartOrder(User user);
    boolean hasCartOrder(User user);

    Order addOrder(User user);
    void updateOrder(Order order);

    OrderItem getItem(Order order, Product product);
    Order addItem(User user, Product product, int quantity);
    Order updateItem(User user, Product product, int quantity);
    Order removeItem(User user, Product product);
}
