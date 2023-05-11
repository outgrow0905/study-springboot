package com.springboot.advanced.ch4.proxyfactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcreteService {

    public void save() {
        log.info("ConcreteService save()");
    }

    public void find() {
        log.info("ConcreteService find()");
    }
}
