package com.example.hello.spring.boot.ctrl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping()
    public String sayHi() {
        return "Hello Spring Boot";
    }

}
