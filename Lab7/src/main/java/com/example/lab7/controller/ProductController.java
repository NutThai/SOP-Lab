package com.example.lab7.controller;

import com.example.lab7.pojo.Product;
import com.example.lab7.service.ProductService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ProductService productService;

    @GetMapping
    public Product serviceGetProducts(){
        return new Product();
    }



}
