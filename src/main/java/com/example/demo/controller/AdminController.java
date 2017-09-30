package com.example.demo.controller;

import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Devon Ravihansa on 9/28/2017.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/dashboard")
    public String dashboard(){
        return "admin/dashboard";
    }

    @RequestMapping("/login")
    public String login(){
        return "admin/login";
    }

    @RequestMapping("/views/products")
    public String getProducts(){
        return "views/product::all";
    }

    @RequestMapping("/views/products/detail")
    public String getProduct(){
        return "views/product::detail";
    }

    @RequestMapping("/views/products/create")
    public String addProduct(){
        return "views/product::create";
    }
}
