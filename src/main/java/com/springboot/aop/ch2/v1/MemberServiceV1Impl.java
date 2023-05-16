package com.springboot.aop.ch2.v1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@ClassAop
public class MemberServiceV1Impl implements MemberServiceV1{
    @Override
    @MethodAop("test value")
    public String hello(String param) {
        return "ok";
    }

    public String internalHello(String param) {
        return "ok";
    }
}
