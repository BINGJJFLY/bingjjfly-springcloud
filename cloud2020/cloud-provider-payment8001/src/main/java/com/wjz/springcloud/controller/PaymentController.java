package com.wjz.springcloud.controller;

import com.wjz.springcloud.entities.CommonResult;
import com.wjz.springcloud.entities.Payment;
import com.wjz.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    private String serverPort;
    @Resource
    private PaymentService paymentService;

    @PostMapping(value ="/payment/create")
    public CommonResult create(@RequestBody Payment payment){

        int result=paymentService.create(payment);
        log.info("******结果："+result);

        if(result>0){
            return new CommonResult(200,"插入数据库成功"+serverPort,result);
        }else {
            return new CommonResult(444,"插入数据库失败"+serverPort,null);
        }
    }

    @GetMapping(value ="/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){

        Payment payment = paymentService.getPaymentById(id);
        log.info("******结果："+payment+"啊哈哈");

        if(payment!=null){
            return new CommonResult(200,"查询成功"+serverPort,payment);
        }else {
            return new CommonResult(444,"没有对应记录"+serverPort,null);
        }
    }
}
