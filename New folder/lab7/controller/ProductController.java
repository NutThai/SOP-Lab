package com.example.lab7.controller;

import com.example.lab7.pojo.Product;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping(value = "/serviceAddProduct")
    public boolean serviceAddProduct(@RequestBody Product a) {
        boolean res = (boolean) rabbitTemplate.convertSendAndReceive("ProductExchange", "add", a);
        return res;
    }

    @PostMapping(value = "/serviceUpdateProduct")
    public List serviceUpdateProduct(@RequestBody Product a) {
        List res = (List)rabbitTemplate.convertSendAndReceive("ProductExchange", "update", a);
        return res;
    }
    @PostMapping(value = "/serviceDeleteProduct/{id}")
    public boolean serviceDeleteProduct(@PathVariable String id) {
        boolean res = (boolean) rabbitTemplate.convertSendAndReceive("ProductExchange", "delete", id);
        return res;
    }
    @GetMapping(value = "/serviceGetProductName/{name}")

    public Product serviceGetProductName(@PathVariable String name) {
        Product res = (Product) rabbitTemplate.convertSendAndReceive("ProductExchange", "getname", name);
        return res;
    }
    @GetMapping(value = "/serviceGetAllProduct")

    public List<Product> serviceGetAllProduct() {
        List<Product> res = (List<Product>) rabbitTemplate.convertSendAndReceive("ProductExchange", "getall", "");
        return res;
    }

}
