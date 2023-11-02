package com.example.ordersservice;

import com.example.ordersservice.Data.OrderEntity;
import com.example.ordersservice.Data.OrderRepository;
import com.example.ordersservice.Event.OrderApprovedEvent;
import com.example.ordersservice.Event.OrderCreatedEvent;
import com.example.ordersservice.Event.OrderRejectedEvent;
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
    @EventHandler
    public void on(OrderApprovedEvent event){
        OrderEntity orderEntity = orderRepository.findByOrderId(event.getOrderId());
        if(orderEntity == null){
            System.out.println("return null");
            return;
        }
        orderEntity.setOrderStatus(event.getOrderStatus());
        orderRepository.save(orderEntity);
    }
    @EventHandler
    public void on(OrderRejectedEvent event){
        OrderEntity orderEntity = orderRepository.findByOrderId(event.getOrderId());
        if(orderEntity == null){
            return;
        }
        orderEntity.setOrderStatus(event.getOrderStatus());
        orderRepository.save(orderEntity);
    }
}
