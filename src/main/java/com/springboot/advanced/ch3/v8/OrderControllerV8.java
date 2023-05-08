package com.springboot.advanced.ch3.v8;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping
@ResponseBody
public interface OrderControllerV8 {
    @GetMapping("/v8/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/v8/no-log")
    String noLog();
}
