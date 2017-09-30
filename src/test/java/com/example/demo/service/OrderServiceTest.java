package com.example.demo.service;

import com.example.demo.domain.Order;
import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.dto.UserDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Set;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Devon Ravihansa on 9/25/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    public void orderTest() throws Exception {
        User user = userService.getUser("user2");
        Product product = productService.getProduct("YH-001");
        Order order = orderService.addItem(user, product, 10);

        /*Cart cart = new Cart();
        for (OrderItem item : order.getOrderItems()) {
            cart.addItem(modelMapper.map(item.getProduct(), ProductDto.class), item.getQuantity());
        }*/
    }

    public void someTest() throws Exception {
        mockMvc
                .perform(get("/rest/cart").with(user("user2")))
                .andDo(print());
    }

    public void cartTest() throws Exception {
        mockMvc
                .perform(post("/rest/cart").with(user("user2")).param("sku", "MP-001").param("quantity", "5"))
                .andDo(print());
    }

    public void addOrder(){
        User user = userService.getUser("user2");
        Order order = orderService.getCartOrder(user);
    }

    @Test
    public void userTest(){
        User user = userService.getUser("user2");
        UserDto userDto = modelMapper.map(user, UserDto.class);

        userDto.setRoles(modelMapper.map(user.getUserRoles(), new TypeToken<Set<String>>() {}.getType()));
    }
}
