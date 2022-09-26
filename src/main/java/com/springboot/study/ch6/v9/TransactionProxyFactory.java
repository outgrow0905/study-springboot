package com.springboot.study.ch6.v9;


import com.springboot.study.ch4.v6.UserDaoInterface;
import com.springboot.study.ch4.v7.UserServiceInterfaceV1;
import com.springboot.study.ch6.v8.TransactionHandler;
import java.lang.reflect.Proxy;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.transaction.PlatformTransactionManager;


@Getter
@Setter
public class TransactionProxyFactory implements FactoryBean<Object> {

    private Object target;
    private PlatformTransactionManager transactionManager;
    private Class<?> serviceInterface;

    @Override
    public Object getObject() throws Exception {
        return Proxy.newProxyInstance(
            getClass().getClassLoader(),
            new Class[]{serviceInterface},
            new TransactionHandler(target, transactionManager)
        );
    }

    @Override
    public Class<?> getObjectType() {
        return serviceInterface;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
