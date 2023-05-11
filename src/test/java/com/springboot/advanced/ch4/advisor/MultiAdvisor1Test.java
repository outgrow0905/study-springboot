package com.springboot.advanced.ch4.advisor;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

@Slf4j
public class MultiAdvisor1Test {
    @Test
    void advisor1() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory factory1 = new ProxyFactory(target);
        factory1.addAdvisor(new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice1()));
        ServiceInterface proxiedService1 = (ServiceInterface) factory1.getProxy();

        ProxyFactory factory2 = new ProxyFactory(proxiedService1);
        factory2.addAdvisor(new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice2()));
        ServiceInterface proxiedService2 = (ServiceInterface) factory2.getProxy();

        proxiedService2.save();
    }

    /**
     * 실제 클래스에 여러개의 advisor를 적용한다고 해서 위의 advisor1처럼 여러개의 프록시가 생성되지 않는다. 참고하자.
     */
    @Test
    void advisor2() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory factory1 = new ProxyFactory(target);
        factory1.addAdvisor(new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice2()));
        factory1.addAdvisor(new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice1()));
        ServiceInterface proxiedService1 = (ServiceInterface) factory1.getProxy();

        proxiedService1.save();
    }

    @Slf4j
    static class Advice1 implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("Advice1 invoke()");
            return invocation.proceed();
        }
    }

    @Slf4j
    static class Advice2 implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("Advice2 invoke()");
            return invocation.proceed();
        }
    }
}
