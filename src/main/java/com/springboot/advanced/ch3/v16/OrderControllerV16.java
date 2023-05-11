package com.springboot.advanced.ch3.v16;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping
@ResponseBody
public interface OrderControllerV16 {
    @GetMapping("/v16/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/v16/no-log")
    String noLog();
}
