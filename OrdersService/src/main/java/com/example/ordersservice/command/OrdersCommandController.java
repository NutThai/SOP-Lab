package com.example.ordersservice.command;

import com.example.ordersservice.RestModel.OrderModel;
import com.example.ordersservice.RestModel.OrderStatus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrdersCommandController {
    private final Environment env;
    private final CommandGateway commandGateway;
    @Autowired
    public OrdersCommandController(Environment env, CommandGateway commandGateway){
        this.env = env;
        this.commandGateway = commandGateway;
    }
    @PostMapping
    public String createOrder(@RequestBody OrderModel model){
        CreateOrderCommand command = CreateOrderCommand.builder()
                .productId(model.getProductId())
                .quantity(model.getQuantity())
                .addressId(model.getAddressId())
                .userId("1212312121")
                .orderStatus(OrderStatus.CREATED)
                .orderId(UUID.randomUUID().toString())
                .build();
        String result;
        try{
            result = commandGateway.sendAndWait(command);
        }catch(Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }
}
