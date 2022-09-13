package com.springboot.study.ch3.v3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CalculatorContextV3 {
    public Integer calculate(final String filepath, LineCallback callback) {
      BufferedReader bufferedReader = null;
      Integer result = null;

      try {
        bufferedReader = new BufferedReader(
            new FileReader(String.format("src/main/resources/ch3/%s", filepath)));

        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            if (result == null) {
                result = Integer.valueOf(line);
            } else {
                result = callback.callback(Integer.valueOf(line), result);
            }
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
