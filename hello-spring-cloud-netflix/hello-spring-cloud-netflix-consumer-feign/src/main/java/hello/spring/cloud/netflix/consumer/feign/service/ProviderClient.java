package hello.spring.cloud.netflix.consumer.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "hello-spring-cloud-netflix-provider")
public interface ProviderClient {

    @GetMapping(value = "hi")
    public String sayHi(@RequestParam(value = "msg", required = false) String msg);

}
