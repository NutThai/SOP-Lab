package com.example.lab4.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Cashier {
    private int b1000, b500, b100, b20, b10, b5;
    private Change change;
    @GetMapping(value = "/getChange/{amount}")
    public Change getChange(@PathVariable int amount){
        b1000 = amount / 1000;
        amount %= 1000;
        b500 = amount / 500;
        amount %= 500;
        b100 = amount / 100;
        amount %= 100;
        b20 = amount / 20;
        amount %= 20;
        b10 = amount / 10;
        amount %= 10;
        b5 = amount / 5;
        amount %= 5;


        return change = new Change(b1000, b500, b100, b20, b10, b5, amount);
    }
}
