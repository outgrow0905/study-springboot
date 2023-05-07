package com.springboot.advanced.ch2.v7;

import com.springboot.advanced.ch2.v7.strategy.StrategyInFieldContext;
import com.springboot.advanced.ch2.v7.strategy.StrategyInParameterContext;
import com.springboot.advanced.ch2.v7.strategy.StrategyLogic1;
import com.springboot.advanced.ch2.v7.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class StrategyTest {

    @Test
    void beforeStrategy() {
        logic1();
        logic2();
    }

    @Test
    void strategyInFieldWithSetter() {
        // setter로 strategy를 관리하면 동시성 문제가 발생할 위험이 있다.
        StrategyInFieldContext context = new StrategyInFieldContext();
        context.setStrategy(new StrategyLogic1());
        context.execute();
        context.setStrategy(new StrategyLogic2());
        context.execute();
    }

    @Test
    void strategyInFieldWithConstructor() {
        // 생성자 주입으로 대체한다면 context를 싱글톤으로 사용하기가 어렵다.
        StrategyInFieldContext context1 = new StrategyInFieldContext(new StrategyLogic1());
        context1.execute();
        StrategyInFieldContext context2 = new StrategyInFieldContext(new StrategyLogic2());
        context2.execute();
    }

    @Test
    void strategyInFieldWithConstructorAndLambda() {
        StrategyInFieldContext context1 = new StrategyInFieldContext(() -> log.info("logic 1 execute.."));
        context1.execute();
        StrategyInFieldContext context2 = new StrategyInFieldContext(() -> log.info("logic 2 execute.."));
        context2.execute();
    }

    @Test
    void strategyInParameter() {
        // strategy를 메서드 파라미터로 받으면 동시성문제가 해결된다. 쓰레드별로 공유하지 않기 때문이다.
        // 또한, 싱글톤으로 사용도 가능하다. strategy를 인스턴스변수로 보관하지 않기 때문이다.
        StrategyInParameterContext context = new StrategyInParameterContext();
        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic2());
    }

    @Test
    void strategyInParameterWithLambda() {
        StrategyInParameterContext context = new StrategyInParameterContext();
        context.execute(() -> log.info("logic 1 execute.."));
        context.execute(() -> log.info("logic 2 execute.."));
    }

    private void logic1() {
        long startMs = System.currentTimeMillis();

        // business logic start..
        System.out.println("logic 1 execute..");
        // business logic end..

        long endMs = System.currentTimeMillis();
        long boxUsage = endMs - startMs;
        log.info("boxUsage: {}", boxUsage);
    }

    private void logic2() {
        long startMs = System.currentTimeMillis();

        // business logic start..
        System.out.println("logic 1 execute..");
        // business logic end..

        long endMs = System.currentTimeMillis();
        long boxUsage = endMs - startMs;
        log.info("boxUsage: {}", boxUsage);
    }
}
