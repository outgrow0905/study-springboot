package com.springboot.study.ch3.v4;

import java.io.IOException;

public interface LineCallback<T> {
   T callback(Integer line, T result) throws IOException;
}
