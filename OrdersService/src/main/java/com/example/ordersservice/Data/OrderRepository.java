package com.example.ordersservice.Data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<OrderEntity,String> {
    OrderEntity findByOrderId(String orderId);
}
