package com.springboot.advanced.ch3.v14;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping
@ResponseBody
public interface OrderControllerV14 {
    @GetMapping("/v14/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/v14/no-log")
    String noLog(@RequestParam("itemId") String itemId);
}
