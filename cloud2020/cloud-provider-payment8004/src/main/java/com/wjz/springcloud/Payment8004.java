package com.wjz.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableDiscoveryClient
@EnableAutoConfiguration
public class Payment8004 {

    public static void main(String[] args) {
        SpringApplication.run(Payment8004.class, args);
    }
}
