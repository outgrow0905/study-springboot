package com.springboot.advanced.ch3.v14;

import com.springboot.advanced.ch1.trace.LogTrace;
import com.springboot.advanced.ch2.v7.strategy.LogContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class LogInvocationFilterHandler implements InvocationHandler {

    private final Object target;
    private final LogContext context;
    private final String[] patterns;

    public LogInvocationFilterHandler(Object target, LogTrace logTrace, String[] patterns) {
        this.target = target;
        this.context = new LogContext(logTrace);
        this.patterns = patterns;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!PatternMatchUtils.simpleMatch(patterns, method.getName())) {
            return method.invoke(target, args);
        }

        return context.execute(
                String.format("%s %s invoke()", method.getDeclaringClass().getSimpleName(), method.getName()),
                () -> {
                    try {
                        return method.invoke(target, args);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
