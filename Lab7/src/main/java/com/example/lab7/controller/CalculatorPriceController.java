package com.example.lab7.controller;

import com.example.lab7.service.CalculatorPriceService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorPriceController {
    @Autowired
    private CalculatorPriceService calculatorPriceService;
    @GetMapping(value = "/getPrice/{cost}/{profit}")
    public double serviceGetProducts(double cost, double profit){
        return calculatorPriceService.getPrice(cost, profit);
    }
}
