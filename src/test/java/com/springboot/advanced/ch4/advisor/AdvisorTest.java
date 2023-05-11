package com.springboot.advanced.ch4.advisor;

import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

public class AdvisorTest {
    @Test
    void advisor1() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory factory = new ProxyFactory(target);
        DefaultPointcutAdvisor defaultPointcutAdvisor =
                new DefaultPointcutAdvisor(Pointcut.TRUE, new TimeMethodInterceptor());
        factory.addAdvisor(defaultPointcutAdvisor);

        ServiceInterface proxiedService = (ServiceInterface) factory.getProxy();

        proxiedService.find();
        proxiedService.save();
    }

    @Test
    void advisor2() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory factory = new ProxyFactory(target);
        DefaultPointcutAdvisor defaultPointcutAdvisor =
                new DefaultPointcutAdvisor(new MyPointCut(), new TimeMethodInterceptor());
        factory.addAdvisor(defaultPointcutAdvisor);

        ServiceInterface proxiedService = (ServiceInterface) factory.getProxy();

        proxiedService.find();
        proxiedService.save();
    }

    @Test
    void advisor3() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory factory = new ProxyFactory(target);
        NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut();
        nameMatchMethodPointcut.setMappedName("save");
        DefaultPointcutAdvisor defaultPointcutAdvisor =
                new DefaultPointcutAdvisor(nameMatchMethodPointcut, new TimeMethodInterceptor());
        factory.addAdvisor(defaultPointcutAdvisor);

        ServiceInterface proxiedService = (ServiceInterface) factory.getProxy();

        proxiedService.find();
        proxiedService.save();
    }
}
