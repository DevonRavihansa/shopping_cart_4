package com.example.demo.controller;

import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.UserDto;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Devon Ravihansa on 9/28/2017.
 */
@Controller
public class DefaultController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping({"/", "/index"})
    public String index(){
        return "index";
    }

    @RequestMapping("/shop")
    public String shop(Model model){
        model.addAttribute("products", createDtos(productService.getAllProducts()));
        return "shop";
    }

    @RequestMapping("/product/{sku}")
    public String product(@PathVariable("sku")String sku, Model model){
        model.addAttribute("product", createDto(productService.getProduct(sku)));
        return "product";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/signup")
    public String signup(){
        return "signup";
    }

    @RequestMapping("/user/{username}")
    public String user(@PathVariable("username")String username, Model model){
        model.addAttribute("user", createDto(userService.getUser(username)));
        return "user";
    }

    @RequestMapping("/cart")
    public String cart(){
        return "cart";
    }

    private ProductDto createDto(Product product){
        return modelMapper.map(product, ProductDto.class);
    }

    private List<ProductDto> createDtos(List<Product> products){
        return modelMapper.map(products, new TypeToken<List<ProductDto>>(){}.getType());
    }

    private UserDto createDto(User user){
        return modelMapper.map(user, UserDto.class);
    }
}
