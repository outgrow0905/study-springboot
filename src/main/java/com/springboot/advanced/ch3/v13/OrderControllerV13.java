package com.springboot.advanced.ch3.v13;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping
@ResponseBody
public interface OrderControllerV13 {
    @GetMapping("/v13/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/v13/no-log")
    String noLog(@RequestParam("itemId") String itemId);
}
