package com.example.ordersservice.command;

import com.example.ordersservice.RestModel.OrderModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersCommandController {

    @PostMapping
    public void createOrder(@RequestBody OrderModel model){

    }
}
