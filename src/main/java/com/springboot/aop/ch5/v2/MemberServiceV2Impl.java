package com.springboot.aop.ch5.v2;

import org.springframework.stereotype.Service;

@ClassAop
@Service
public class MemberServiceV2Impl implements MemberServiceV2 {

    @Override
    @MethodAop("test value")
    public String hello(String param) {
        return "ok";
    }

    public String internal(String param) {
        return "ok";
    }
}
