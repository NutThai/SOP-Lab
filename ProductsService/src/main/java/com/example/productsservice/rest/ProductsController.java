package com.example.productsservice.rest;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private final Environment env;
    public ProductsController(Environment env){
        this.env = env;
    }
    @PostMapping
    public String createProduct(){
        return "HTTP POST Handled";
    }
    @GetMapping
    public String getProduct(){
        return "HTTP GET Handled "+env.getProperty("local.server.port");

    }
    @PutMapping
    public String upadateProduct(){
        return"HTTP PUT Handled";
    }
    @DeleteMapping
    public String StringProduct(){
        return "HTTP DELETE Handled";
    }
}
