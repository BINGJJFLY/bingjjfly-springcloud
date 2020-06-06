package com.wjz.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@SpringBootApplication
public class OrderSleuthMain80 {

    /**
     * docker-compose.yml
     * zipkin:
     *   image: openzipkin/zipkin:2.12.9
     *   ports:
     *     - "9411:9411"
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(OrderSleuthMain80.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
