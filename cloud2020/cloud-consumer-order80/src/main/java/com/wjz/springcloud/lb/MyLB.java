package com.wjz.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBalancer {

    private final AtomicInteger count = new AtomicInteger(0);

    private int getNextIndex() {
        int current;
        int next;
        do {
            current = this.count.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
        } while (!count.compareAndSet(current, next));
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getNextIndex() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
