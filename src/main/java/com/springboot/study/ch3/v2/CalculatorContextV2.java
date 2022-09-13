package com.springboot.study.ch3.v2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CalculatorContextV2 {
    public Integer calculate(final String filepath, CalculatorCallback callback) {
      BufferedReader bufferedReader = null;
      Integer result = null;
      try {
        bufferedReader = new BufferedReader(
            new FileReader(String.format("src/main/resources/ch3/%s", filepath)));

        result = callback.callback(bufferedReader);
      } catch (IOException e) {
        log.error(e.getMessage());
      } finally {
        try {
          if (null != bufferedReader) {
            bufferedReader.close();
          }
        } catch (IOException e) {}
      }

      log.info("result: {}", result);
      return result;

    }
}
