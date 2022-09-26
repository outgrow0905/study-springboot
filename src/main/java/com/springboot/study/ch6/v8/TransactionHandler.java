package com.springboot.study.ch6.v8;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Slf4j
public class TransactionHandler implements InvocationHandler {
    private PlatformTransactionManager transactionManager;
    private Object target;
    private final Map<String, Method> methodMap = new HashMap<>();

    public TransactionHandler(Object target, PlatformTransactionManager transactionManager) {
        this.target = target;
        this.transactionManager = transactionManager;
        for (Method method : target.getClass().getMethods()) {
            this.methodMap.put(method.getName(), method);
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("invoked method: {}", method.getName());

        long startTime = System.currentTimeMillis();
        Object result = null;

        if (methodMap.get(method.getName()).isAnnotationPresent(MyTransactional.class)) {
            TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
            try {
                result = methodMap.get(method.getName()).invoke(target, args);
                transactionManager.commit(status);
            }  catch (InvocationTargetException e) {
                log.error("TransactionHandler target exception: {}", e.getTargetException().getMessage());
                transactionManager.rollback(status);
            }
        } else {
            result = methodMap.get(method.getName()).invoke(target, args);
        }

        log.info("boxUsage: {}", System.currentTimeMillis() - startTime);
        return result;
    }
}
