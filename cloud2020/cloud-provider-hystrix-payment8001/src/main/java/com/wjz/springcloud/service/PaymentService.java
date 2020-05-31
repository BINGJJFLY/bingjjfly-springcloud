package com.wjz.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String ok(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "ok,id:" + id + "\t" + "哈哈";
    }

    // 去掉生产端服务降级，验证消费端服务降级
/*    @HystrixCommand(fallbackMethod = "timeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })*/
    public String timeout(Integer id) {
//        int age = 10/0;
        int c = 5;
        try {
            TimeUnit.SECONDS.sleep(c);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "timeout,id:" + id + "\t" + "哈哈" + "耗时 "+c+"秒";
    }

    /**
     * 服务超时或者是服务运行异常做服务降级
     * @param id
     * @return
     */
    public String timeoutHandler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "timeout,id:" + id + "\t" + "/(ㄒoㄒ)/~~";
    }

    // 服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),              //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),    //快照时间窗内，请求次数达到1-次才有资格进行熔断
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //快照时间窗，最后10秒的情况进行统计
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),  //快照时间窗内，请求错误率达到多少跳闸
    })
    public String paymentCircuitBreaker(Integer id) {
        if(id < 0){
            throw  new RuntimeException("****id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return  Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(Integer id){
        return "id 不能为负数,请稍后再试， o(╥﹏╥)o id: " + id;
    }
}
