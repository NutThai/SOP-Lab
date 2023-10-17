package com.example.paymenesservice;

import com.example.core.events.PaymentProcessedEvent;
import com.example.paymenesservice.data.PaymentEntity;
import com.example.paymenesservice.data.PaymentRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventsHandler {
    public final PaymentRepository paymentRepository;
    public PaymentEventsHandler(PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }
    @EventHandler
    public void on(PaymentProcessedEvent event){
        PaymentEntity paymentEntity = new PaymentEntity();
        BeanUtils.copyProperties(event, paymentEntity);
        paymentRepository.save(paymentEntity);
    }
}
