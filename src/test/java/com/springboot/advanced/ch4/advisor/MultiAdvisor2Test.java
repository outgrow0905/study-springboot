package com.springboot.advanced.ch4.advisor;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

@Slf4j
public class MultiAdvisor2Test {
    /**
     * 이 예시는 프록시된 클래스 안에 프록시된 클래스를 주입하여 호출하는 것을 시뮬레이션한다.
     */
    @Test
    void multiAdvisor1() {
        // proxy2
        MyFunctionalInterface myLogic2 = new MyLogic2();
        ProxyFactory factory2 = new ProxyFactory(myLogic2);
        factory2.addAdvisor(new DefaultPointcutAdvisor(new Advice2()));
        MyFunctionalInterface proxiedMyLogic2 = (MyFunctionalInterface) factory2.getProxy();

        // proxy1
        MyFunctionalInterface myLogic1 = new MyLogic1(proxiedMyLogic2); // inject proxied instance
        ProxyFactory factory1 = new ProxyFactory(myLogic1);
        factory1.addAdvisor(new DefaultPointcutAdvisor(new Advice1()));
        MyFunctionalInterface proxiedMyLogic1 = (MyFunctionalInterface) factory1.getProxy();

        // call
        proxiedMyLogic1.call();
    }


    @FunctionalInterface
    interface MyFunctionalInterface {
        void call();
    }

    static class MyLogic1 implements MyFunctionalInterface {

        private final MyFunctionalInterface myFunctionalInterface;

        MyLogic1(MyFunctionalInterface myFunctionalInterface) {
            this.myFunctionalInterface = myFunctionalInterface;
        }

        @Override
        public void call() {
            log.debug("MyLogic1 call()");
            myFunctionalInterface.call();
        }
    }

    static class MyLogic2 implements MyFunctionalInterface {
        @Override
        public void call() {
            log.debug("MyLogic2 call()");
        }
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
