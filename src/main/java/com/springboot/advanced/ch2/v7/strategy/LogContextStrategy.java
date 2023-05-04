package com.springboot.advanced.ch2.v7.strategy;

@FunctionalInterface
public interface LogContextStrategy<T> {
    T action();
}
