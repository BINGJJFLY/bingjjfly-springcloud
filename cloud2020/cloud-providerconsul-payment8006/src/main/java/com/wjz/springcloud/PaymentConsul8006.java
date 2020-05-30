package com.wjz.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PaymentConsul8006 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentConsul8006.class, args);
    }
}
