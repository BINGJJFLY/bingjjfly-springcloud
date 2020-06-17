package com.wjz.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaMain7001 {

    /**
     * Eureka中自带了Ribbon，实现了负载均衡
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7001.class, args);
    }
}
