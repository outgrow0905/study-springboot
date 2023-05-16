package com.springboot.aop.ch1.v6;

import org.aspectj.lang.annotation.Pointcut;

public class PointcutsV6 {
    @Pointcut("execution(* com.springboot.aop.ch1.v6..*(..))")
    public void logV6Package(){}

    @Pointcut("execution(* *..*ServiceV6.*(..))")
    public void logServiceV6(){}

    @Pointcut("logV6Package() && logServiceV6()")
    public void logServiceV6InV6Package() {}
}
