package com.springboot.study.ch6.proxy.v2;

import static org.junit.jupiter.api.Assertions.*;

import com.springboot.study.ch4.v7.UserServiceInterfaceV1;
import com.springboot.study.ch4.v7.UserServiceTxV1;
import com.springboot.study.ch4.v7.UserServiceV4;
import com.springboot.study.ch6.proxy.v1.Hello;
import com.springboot.study.ch6.proxy.v1.TargetHello;
import java.lang.reflect.Proxy;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TimingDynamicInvocationHandlerTest {

    @Test
    void dynamic_proxy() throws Exception {
        Hello hello = (Hello) Proxy.newProxyInstance(
            getClass().getClassLoader(),
            new Class[]{Hello.class},
            new TimingDynamicInvocationHandler(new TargetHello())
        );

        hello.sayHello("hello");
    }
}