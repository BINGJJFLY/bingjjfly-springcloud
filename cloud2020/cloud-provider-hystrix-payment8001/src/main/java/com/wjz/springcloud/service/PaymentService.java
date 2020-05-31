package com.wjz.springcloud.service;

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
}
