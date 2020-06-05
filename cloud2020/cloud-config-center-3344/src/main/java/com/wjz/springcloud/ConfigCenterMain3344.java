package com.wjz.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigCenterMain3344 {

    /**
     * 全局通知：
     * CMD窗口发送POST请求：curl -X POST http://127.0.0.1:3344/actuator/bus-refresh
     * 定点通知：
     * CMD窗口发送POST请求：curl -X POST http://127.0.0.1:3344/actuator/bus-refresh/cloud-config-client:3355
     *
     * docker-compose.yml文件
     * rabbitmq:
     *   image: rabbitmq:3.7.14-management
     *   ports:
     *     - "5672:5672"
     *     - "15672:15672"
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterMain3344.class, args);
    }
}
