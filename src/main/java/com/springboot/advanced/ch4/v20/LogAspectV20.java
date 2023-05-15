package com.springboot.advanced.ch4.v20;

import com.springboot.advanced.ch1.trace.LogTrace;
import com.springboot.advanced.ch2.v7.strategy.LogContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspectV20 {
    private final LogContext context;

    public LogAspectV20(LogTrace logTraceV5) {
        this.context = new LogContext(logTraceV5);
    }

    @Around("execution(* com.springboot.advanced.ch4.v20..*(..))" +
            "&& !execution(* com.springboot.advanced.ch4.v20..noLog(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        return context.execute(joinPoint.getSignature().toShortString(),
                () -> {
                    try {
                        return joinPoint.proceed();
                    } catch (Throwable e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
