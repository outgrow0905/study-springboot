package com.springboot.aop.ch1.v5;

import org.aspectj.lang.annotation.Pointcut;

public class PointcutsV5 {
    @Pointcut("execution(* com.springboot.aop.ch1.v5..*(..))")
    public void logV5Package(){}

    @Pointcut("execution(* *..*ServiceV5.*(..))")
    public void logServiceV5(){}

    @Pointcut("logV5Package() && logServiceV5()")
    public void logServiceV5InV5Package() {}
}
