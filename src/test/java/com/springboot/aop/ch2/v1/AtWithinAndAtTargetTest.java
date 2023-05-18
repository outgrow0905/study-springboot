package com.springboot.aop.ch2.v1;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Slf4j
public class AtWithinAndAtTargetTest {

    private ApplicationContext context = new AnnotationConfigApplicationContext(
            Config.class, AtTargetAtWithinAspect.class);

    @Test
    void compareAtTargetAndAtWithin() {
        Child child = context.getBean("child", Child.class);

        /**
         * childMethod()의 경우는 @target, @within 두 pointcut에 걸리는게 이상하지 않다.
         *
         * 그러나, parentMethod()의 경우는 @target에서는 match가 되고, @within에서는 match가 되지 않았다.
         * @target의 경우는 부모 클래스의 메서드까지 프록시로 포함하지만
         * @within의 경우는 부모 클래스의 메서드는 포함하지 않는다.
         */
        child.childMethod();
        child.parentMethod();
    }

    static class Config {
        @Bean
        public Child child() {
            return new Child();
        }

//        @Bean
//        public Hello hello() {
//            return new Hello();
//        }
    }


    static class Parent {
        public void parentMethod(){}
    }

    @ClassAop
    static class Child extends Parent {
        public void childMethod(){}
    }

//    static class Hello {
//        public void hello(){}
//    }

    @Slf4j
    @Aspect
    @EnableAspectJAutoProxy
    static class AtTargetAtWithinAspect {
        @Around("execution(* com.springboot.aop.ch2.v1..*(..)) " +
                "&& @target(com.springboot.aop.ch2.v1.ClassAop)")
        /**
         * 아래와 같이 사용하면 안된다.
         * args, @args, @target의 경우는 실제 생성된 빈을 기반으로 프록시 적용여부를 판단하기 때문에,
         * 모든 빈에 프록시를 적용하려고 한다(?) 이 부분은 이해가 안된다.
         * 따라서, execution을 통해 범위를 축소하여 같이 사용해야 한다고...한다.
         *
         * args, @args, @target 은 execution과 같이 사용해야 한다 정도로 일단 넘어가자.
         */
//        @Around("@target(com.springboot.aop.ch2.v1.ClassAop)")
        public Object atTarget(ProceedingJoinPoint joinPoint) throws Throwable{
            log.info("[@target] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        @Around("execution(* com.springboot.aop.ch2.v1..*(..)) " +
                "&& @within(com.springboot.aop.ch2.v1.ClassAop)")
        public Object atWithin(ProceedingJoinPoint joinPoint) throws Throwable{
            log.info("[@within] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

//        @Around("within(com.springboot.aop.ch2.v1.AtWithinAndAtTargetTest.Hello)")
//        public Object atHello(ProceedingJoinPoint joinPoint) throws Throwable{
//            log.info("[@target] {}", joinPoint.getSignature());
//            return joinPoint.proceed();
//        }
    }
}
