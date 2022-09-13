package com.springboot.study.ch3.v4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CalculatorContextV4 {
    public <T> T calculate(final String filepath, LineCallback<T> callback, T initValue) {
      BufferedReader bufferedReader = null;
      T result = initValue;

      try {
        bufferedReader = new BufferedReader(
            new FileReader(String.format("src/main/resources/ch3/%s", filepath)));

        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            result = callback.callback(Integer.valueOf(line), result);
        }
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
