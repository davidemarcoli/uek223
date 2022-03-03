package com.noseryoung.uek223;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Hidden
public class TestController {
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
}