package com.wjz.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConfigClientMain3355 {

    /**
     * CMD窗口发送POST请求：curl -X POST http://127.0.0.1:3355/actuator/refresh
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientMain3355.class,args);
    }
}
