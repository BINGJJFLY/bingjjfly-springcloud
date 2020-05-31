package com.wjz.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wjz.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
// 全局服务降级配置，避免一个方法对应一个降级方法
// 使用FeignClient全局服务降级方式进一步解耦
@DefaultProperties(defaultFallback = "globalHandler")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping(value ="/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfo_OK(id);
    }

    @GetMapping(value ="/consumer/payment/hystrix/timeout/{id}")
    // 去掉精准服务降级配置
    /*@HystrixCommand(fallbackMethod = "timeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })*/
    // 全局服务降级配置，长时间调用会造成降级，如阻塞5秒,@see HystrixCommandProperties.default_executionTimeoutInMilliseconds
    // 使用FeignClient全局服务降级方式进一步解耦
    @HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        // 使用FeignClient全局服务降级方式进一步解耦且去掉@HystrixCommand注解时，不会服务降级
        int age = 10/0;
        return paymentHystrixService.paymentInfo_TimeOut(id);
    }

    /**
     * 服务超时或者是服务运行异常做服务降级
     * @param id
     * @return
     */
//    public String timeoutHandler(Integer id) {
//        return "我是消费端，服务端系统繁忙请稍后重试/(ㄒoㄒ)/~~";
//    }

    public String globalHandler() {
        return "全局服务降级，系统繁忙请稍后重试/(ㄒoㄒ)/~~";
    }
}
