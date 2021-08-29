package com.example.hello.spring.cloud.netflix.consumer.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "hello-spring-cloud-netflix-provider", fallback = ProviderClient.ProviderClientHystrix.class)
public interface ProviderClient {

    @GetMapping(value = "hi")
    public String sayHi(@RequestParam(value = "msg", required = false) String msg);

    @Component
    public class ProviderClientHystrix implements ProviderClient{

        @Override
        public String sayHi(String msg) {
            return "Hi，your message is : " + msg + " but request error.";
        }

    }
}
