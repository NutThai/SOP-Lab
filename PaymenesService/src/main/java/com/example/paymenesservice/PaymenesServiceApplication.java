package com.example.paymenesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ AxonXstreamConfig.class })
public class PaymenesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymenesServiceApplication.class, args);
    }

}
