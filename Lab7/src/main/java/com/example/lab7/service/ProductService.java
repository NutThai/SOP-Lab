package com.example.lab7.service;

import com.example.lab7.pojo.Product;
import com.example.lab7.repository.ProductRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@EnableCaching
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @CacheEvict(value = "product", key = "'product'")
    @RabbitListener(queues = "AddProductQueue")
    public boolean addProduct(Product mes) {
        productRepository.save(mes);
        return true;
    }
    @CachePut(value = "product", key = "'product'")
    @RabbitListener(queues = "UpdateProductQueue")
    public List<Product> updateProduct(Product mes) {
        productRepository.save(mes);
        return productRepository.findAll();
    }

    @CacheEvict(value = "product", key = "'product'")
    @RabbitListener(queues = "DeleteProductQueue")
    public boolean deleteProduct(String mes) {
        try {
            productRepository.deleteById(mes);
            return true;
        } catch (Exception err) {
            return false;
        }
    }
    @Cacheable(value = "product", key = "'product'")
    @RabbitListener(queues = "GetAllProductQueue")
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @RabbitListener(queues = "GetNameProductQueue")
    public Product getProductByName(String mes) {
        return productRepository.findByName(mes);
    }
}   
