package com.springboot.study.ch3.v2;

import java.io.BufferedReader;
import java.io.IOException;

public interface CalculatorCallback {
   Integer callback(BufferedReader bufferedReader) throws IOException;
}
