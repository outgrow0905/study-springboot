package com.springboot.advanced.ch2.v1;


import com.springboot.advanced.ch2.v1.template.AbstractTemplate;
import com.springboot.advanced.ch2.v1.template.SubClassLogic1;
import com.springboot.advanced.ch2.v1.template.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {
    @Test
    void templateMethod() {
        AbstractTemplate template1 = new SubClassLogic1();
        template1.execute();
        AbstractTemplate template2 = new SubClassLogic2();
        template2.execute();
    }

    @Test
    void templateMethodWithAnonymous() {
        new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("logic1 progress..");
            }
        }.execute();

        new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("logic2 progress..");
            }
        }.execute();
    }
}
