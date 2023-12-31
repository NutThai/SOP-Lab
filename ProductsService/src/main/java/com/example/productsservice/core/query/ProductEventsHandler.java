package com.example.productsservice.core.query;

import com.example.core.events.ProductReservationCancelledEvent;
import com.example.core.events.ProductReservedEvent;
import com.example.productsservice.core.data.ProductEntity;
import com.example.productsservice.core.data.ProductRepository;
import com.example.productsservice.core.event.ProductCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductEventsHandler {
    private final ProductRepository productRepository;
    public ProductEventsHandler(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @EventHandler
    public void on(ProductCreatedEvent event){
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(event, productEntity);
        productRepository.save(productEntity);
    }
    @EventHandler
    public void on(ProductReservedEvent event){
        ProductEntity productEntity = productRepository.findByProductId(event.getProductId());
        productEntity.setQuantity(productEntity.getQuantity() - event.getQuantity());
        productRepository.save(productEntity);
    }
    @EventHandler
    public void on(ProductReservationCancelledEvent event){
        ProductEntity productEntity = productRepository.findByProductId(event.getProductId());
        productEntity.setQuantity(productEntity.getQuantity()+ event.getQuantity());
        productRepository.save(productEntity);
    }
}
