package com.example.hello.spring.cloud.netflix.consumer.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProviderService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "sayHiError")
    public String sayHi(String msg) {
        return restTemplate.getForObject("http://hello-spring-cloud-netflix-provider/hi?msg=" + msg, String.class);
    }

    public String sayHiError(String msg){
        return "Hi，your message is : " + msg + " but request error.";
    }

}
