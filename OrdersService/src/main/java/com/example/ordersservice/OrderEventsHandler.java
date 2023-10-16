package com.example.ordersservice;

import com.example.ordersservice.Data.OrderEntity;
import com.example.ordersservice.Data.OrderRepository;
import com.example.ordersservice.Event.OrderCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class OrderEventsHandler {
    private final OrderRepository orderRepository;
    public OrderEventsHandler(OrderRepository orderRepository){this.orderRepository = orderRepository;}
    @EventHandler
    public void on(OrderCreatedEvent event){
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(event, orderEntity);
        orderRepository.save(orderEntity);
    }
}
