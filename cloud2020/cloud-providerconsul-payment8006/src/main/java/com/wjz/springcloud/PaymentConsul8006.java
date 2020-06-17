package com.wjz.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PaymentConsul8006 {

    /**
     * Consul可以作为 服务注册中心、配置中心、消息总线，go语言，https://www.consul.io/docs
     * 启动Consul：consul.exe agent -dev
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(PaymentConsul8006.class, args);
    }
}
