package com.springboot.aop.ch5.v1;

@ClassAop
public class MemberServiceV1Impl implements MemberServiceV1 {
    @Override
    @MethodAop("test value")
    public String hello(String param) {
        return "ok";
    }

    public String internal(String param) {
        return "ok";
    }
}
