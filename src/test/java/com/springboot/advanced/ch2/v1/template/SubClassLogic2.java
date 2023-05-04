package com.springboot.advanced.ch2.v1.template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SubClassLogic2 extends AbstractTemplate{

    @Override
    protected void call() {
        log.info("logic2progress..");
    }
}
