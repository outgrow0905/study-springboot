package com.springboot.study.ch3.v3;

import java.io.BufferedReader;
import java.io.IOException;

public interface LineCallback {
   Integer callback(Integer line, Integer result) throws IOException;
}
