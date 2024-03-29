package com.springboot.advanced.ch4.proxyfactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServiceImpl implements ServiceInterface {
    @Override
    public void save() {
      log.info("ServiceImpl save()");
    }

    @Override
    public void find() {
        log.info("ServiceImpl find()");
    }
}
