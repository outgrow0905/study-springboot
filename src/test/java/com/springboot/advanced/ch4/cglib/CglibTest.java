package com.springboot.advanced.ch4.cglib;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {
    @Test
    void cglib() {
        ConcreteService target = new ConcreteService();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ConcreteService.class); // 상속을 사용하는 것으로 추측
        enhancer.setCallback(new TimeMethodInterceptor(target));
        ConcreteService proxiedConcreteService = (ConcreteService)enhancer.create();

        log.info("targetClass: {}", target.getClass());
        log.info("proxiedClass: {}", proxiedConcreteService.getClass());

        proxiedConcreteService.find();
        proxiedConcreteService.save();
    }
}
