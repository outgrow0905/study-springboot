package com.springboot.java.ch9;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class MyBankTest {
    @Test
    void collectBankMoney() throws ExecutionException, InterruptedException {
        MyBank myBank = new MyBank();
        myBank.collectBankMoney();
        System.out.println(myBank.getTotalMoney());
    }
}