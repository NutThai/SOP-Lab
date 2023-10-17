package com.example.ordersservice.Event;

import com.example.ordersservice.RestModel.OrderStatus;
import lombok.Value;

@Value
public class OrderApprovedEvent {
    String orderId;
    OrderStatus orderStatus = OrderStatus.APPROVED;
}
