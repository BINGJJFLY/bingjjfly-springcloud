package com.wjz.spingcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MainApp8401 {

    /**
     * QPS: 请求数阈值
     * Sentinel规则持久化到Nacos中：
     * Nacos中添加配置信息：
     * [
     *     {
     *         "resource": "/rateLimit/byUrl", // 资源名
     *         "limitApp": "default", // 针对来源
     *         "grade": 1, // 阈值类型：1QPS/0线程数
     *         "count": 1, // 单机阈值
     *         "strategy": 0, // 流控模式：0直接；1关联；2链路
     *         "controlBehavior": 0, // 流控效果：0快速失败；1Warm up;2排队等待
     *         "clusterMode": false // 是否集群
     *     }
     * ]
     * 线程数：执行目标方法的线程数阈值
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(MainApp8401.class,args);
    }
}
