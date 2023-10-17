package com.example.ordersservice.Event;

import com.example.ordersservice.RestModel.OrderStatus;
import lombok.Value;

@Value
public class OrderRejectedEvent {
    String orderId;
    String reason;
    OrderStatus orderStatus = OrderStatus.REJECTED;
}
