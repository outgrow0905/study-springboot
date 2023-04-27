package com.springboot.java.ch9;

import com.springboot.java.ch9.v1.MyBank;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

class MyBankTest {
    @Test
    void collectBankMoney() throws ExecutionException, InterruptedException {
        MyBank myBank = new MyBank();
        myBank.collectBankMoney();
        System.out.println(myBank.getTotalMoney());
    }
}