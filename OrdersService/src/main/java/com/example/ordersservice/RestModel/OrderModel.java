package com.example.ordersservice.RestModel;

import lombok.Data;

@Data
public class OrderModel {
    private String productId;
    private int quantity;
    private String addressId;
}
