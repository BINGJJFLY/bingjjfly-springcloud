package com.wjz.spingcloud.alibaba.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        /*try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println(Thread.currentThread().getName());
        return "----testA";
    }

    @GetMapping("/testB")
    public String testB() {
        return "----testB";
    }

    @GetMapping("/testC")
    public String testC() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "----testC 降级-RT";
    }

    @GetMapping("/testD")
    public String testD() {
        int i = 9 / 0;
        return "----testD 降级-异常比例";
    }

    @GetMapping("/testE")
    public String testE() {
        int i = 9 / 0;
        return "----testE 降级-异常数";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false)String p1,
                             @RequestParam(value = "p2",required = false)String p2) {
        return "----testHotKey";
    }
    public String deal_testHotKey(String p1, String p2, BlockException exception) {
        return "----deal_testHotKey, o(╥﹏╥)o"; // sentinel的默认提示都是： Blocked by Sentinel (flow limiting)
    }

    /**
     * 流控-链路
     * 两个入口Entrance1，Entrance2调用同一个资源echo，只记录Entrance1上的限流，而不关系心Entrance2的使用
     * @param args
     */
    public static void main(String[] args) {
        testFlowRuleForLink();
    }

    public static void initFlowRuleForLink(){
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        //定义流控模式
        rule.setStrategy(RuleConstant.STRATEGY_CHAIN);
        //定义资源名
        rule.setResource("echo"); // /testA
        //定义入口资源
        rule.setRefResource("Entrance1"); // /testB
        //定义阈值类型
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //定义阈值
        rule.setCount(2);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

    public static void testFlowRuleForLink(){
        initFlowRuleForLink();
        for (int i = 0; i < 5; i++) {
            ContextUtil.enter("Entrance1"); // /testB
            Entry entry = null;
            try {
                entry = SphU.entry("echo"); // /testA
                System.out.println("访问成功");
            } catch (BlockException e) {
                System.out.println("网络异常，请刷新！");
            }finally {
                if (entry != null){
                    entry.exit();
                }
            }
        }
    }
}
