package com.example.ordersservice.Data;

import com.example.ordersservice.RestModel.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name="orders")
@Data
public class OrderEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1280779077952499043L;
    @Id
    @Column(unique = true)
    public String orderId;
    private String productId;
    private String userId;
    private int quantity;
    private String addressId;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
