package com.springboot.aop.ch1.v7;

import org.aspectj.lang.annotation.Pointcut;

public class PointcutsV7 {
    @Pointcut("execution(* com.springboot.aop.ch1.v7..*(..))")
    public void logV7Package(){}

    @Pointcut("execution(* *..*ServiceV7.*(..))")
    public void logServiceV7(){}

    @Pointcut("logV7Package() && logServiceV7()")
    public void logServiceV7InV7Package() {}
}
