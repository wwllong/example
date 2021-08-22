package com.example.hello.spring.cloud.netflix.provider.ctrl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderCtrl {

    @Value("${server.port}")
    private String port;

    @GetMapping(value = "hi")
    public String sayHi(@RequestParam(value = "msg", required = false) String msg) {
        return String.format("Hi，your message is : %s i am from port : %s", msg, port);
    }

}
