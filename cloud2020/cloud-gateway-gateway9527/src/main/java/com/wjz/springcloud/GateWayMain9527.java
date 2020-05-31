package com.wjz.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class GateWayMain9527 {

    /**
     * CMD窗口：curl http://127.0.0.1:9527/payment/lb --cookie "token=1001" 验证Cookie断言
     * CMD窗口：curl http://127.0.0.1:9527/payment/lb -H "X-Request-Id:1001" 验证Header断言
     * CMD窗口：curl http://127.0.0.1:9527/payment/lb -H "Host:www.jxgyl.com" 验证Host断言
     * CMD窗口：curl http://127.0.0.1:9527/payment/lb?red=greet 验证Query断言
     * CMD窗口：curl http://127.0.0.1:9527/payment/get/1?uname=wjz GlobalFilter过滤
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(GateWayMain9527.class,args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("routeLocator", r -> r.path("/guonei").uri("http://news.baidu.com/guonei"))
                .build();
    }
}
