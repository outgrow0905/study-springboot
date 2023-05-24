package com.springboot.aop.ch5.v2;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest(properties = "spring.aop.proxy-target-class=false") // jdk proxy
@SpringBootTest // cglib
@Slf4j
class MemberServiceTest {
    @Autowired
    MemberServiceV2 memberService;

    /**
     * jdk proxy로 프록시를 만들면 새로 생성된 프록시는 인터페이스 타입이기때문에 MemberServiceV2Impl 타입으로 변환이 불가능하다.
     * 물론, 이렇게 사용하는 것은 좋지 않다. 구체클래스로 주입을 받아서 사용하게 되면 OCP 원칙을 어기게 되기 때문이다.
     * 하지만 테스트나 특정한 상황에서 어쩔수 없이 구체클래스를 사용해야만 하는경우가 있을 수 있다.
     */
    @Autowired
    MemberServiceV2Impl memberServiceImpl;

    @Test
    void typeCast() {
        memberService.hello("helloA");
        memberServiceImpl.hello("helloB"); // jdk proxy이면 불가능
    }
}