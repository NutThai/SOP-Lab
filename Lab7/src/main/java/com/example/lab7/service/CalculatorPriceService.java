package com.example.lab7.service;

import com.example.lab7.pojo.Product;
import com.example.lab7.repository.ProductRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatorPriceService {
    public double getPrice(double cost, double profit) {
        return cost + profit;
    }

}
