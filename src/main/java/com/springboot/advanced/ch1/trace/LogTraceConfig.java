package com.springboot.advanced.ch1.trace;

import com.springboot.advanced.ch1.v4.MyLogTraceV4;
import com.springboot.advanced.ch1.v5.MyLogTraceV5;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {
    @Bean
    public LogTrace logTraceV4() {
        return new MyLogTraceV4();
    }

    @Bean
    public LogTrace logTraceV5() {
        return new MyLogTraceV5();
    }
}
