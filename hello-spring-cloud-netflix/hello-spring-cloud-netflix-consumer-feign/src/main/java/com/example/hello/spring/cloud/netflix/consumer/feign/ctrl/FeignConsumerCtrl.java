package com.example.hello.spring.cloud.netflix.consumer.feign.ctrl;

import com.example.hello.spring.cloud.netflix.consumer.feign.service.ProviderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignConsumerCtrl {

    @Autowired
    private ProviderClient providerClient;

    @GetMapping(value = "hi")
    public String sayHi(@RequestParam(value = "msg", required = false) String msg) {
        return providerClient.sayHi(msg);
    }

}
