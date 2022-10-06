package com.springboot.study.ch6.v10;

import com.springboot.study.ch6.v8.MyTransactional;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Slf4j
@Component
public class MyTransactionAdvice implements MethodInterceptor {
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("MyTransactionAdvice method: {}", invocation.getMethod().getName());

        long startTime = System.currentTimeMillis();
        Object result = null;

//        if (invocation.getMethod().isAnnotationPresent(MyTransactional.class)) {
            TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
            try {
                result = invocation.proceed();
                transactionManager.commit(status);
            }  catch (RuntimeException e) {
                log.error("MyTransactionAdvice exception: {}", e.getMessage());
                transactionManager.rollback(status);
            }
//        } else {
//            result = invocation.proceed();
//        }

        log.info("boxUsage: {}", System.currentTimeMillis() - startTime);
        return result;
    }
}
