package com.springboot.advanced.ch4.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class Reflection {
    @Test
    void reflection1() {
        Hello hello = new Hello();

        log.info("start");
        String resultA = hello.callA();
        log.info("result: {}", resultA);

        log.info("start");
        String resultB = hello.callB();
        log.info("result: {}", resultB);
    }

    @Test
    void reflection2() throws Exception {
        Class helloClass = Class.forName("com.springboot.advanced.ch4.reflection.Reflection$Hello");
        Hello target = new Hello();
        // 아래 호출에서 helloClass에서 메서드를 꺼내왔는데 왜 target을 넣어주어야 하는지 의문일 수 있다.
        // 하지만, 어떤 인스턴스에 호출해야 할지 지정을 해주는게 맞다.
        // 인스턴스가 여러개일 수 있으니 말이다.
        // 혹은 계속해서 새로운 인스턴스를 생성하면서 호출해야 할수도 있으니 말이다.
        Method callA = helloClass.getMethod("callA");
        String resultA = (String) callA.invoke(target);
        log.info("result: {}", resultA);

        Method callB = helloClass.getMethod("callB");
        String resultB = (String) callB.invoke(target);
        log.info("result: {}", resultB);


        // 아래의 예시는 static 메서드의 경우 invoke의 target으로 그냥 클래스자체를 넣어도 작동하는것을 보여준다.
        // static 메서드이니 굳이 인스턴스에 호출하지 않아도 된다.
        Method callC = helloClass.getMethod("callC");
        String resultC = (String) callC.invoke(helloClass);
        log.info("result: {}", resultC);
    }

    @Test
    void reflection3() throws Exception {
        Class helloClass = Class.forName("com.springboot.advanced.ch4.reflection.Reflection$Hello");
        Hello target = new Hello();

        dynamicCall(helloClass.getMethod("callA"), target);
        dynamicCall(helloClass.getMethod("callB"), target);
    }

    private void dynamicCall(Method method, Object target) throws Exception {
        // target과 method만 잘 넘긴다면, 공통의 로직을 수행하는 일종의 프록시기능을 하는 메서드가 된다.
        // 예시처럼 꼭 Hello 클래스가 아니더라도 어떤 클래스의 어떤 메서드를 넘겨도 로그를 찍어주는 메서드가 되는것이다.
        log.info("start");
        String result = (String) method.invoke(target);
        log.info("result: {}", result);
    }

    @Slf4j
    static class Hello  {
        public String callA() {
            log.info("callA()");
            return "A";
        }
        public String callB() {
            log.info("callB()");
            return "B";
        }

        public static String callC() {
            log.info("callB()");
            return "C";
        }

    }
}
