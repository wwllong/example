package hello.spring.cloud.netflix.consumer.ribbon.ctrl;

import hello.spring.cloud.netflix.consumer.ribbon.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RibbonConsumerCtrl {

    @Autowired
    private ProviderService providerService;

    @GetMapping(value = "hi")
    public String sayHi(@RequestParam(value = "msg", required = false) String msg) {
        return providerService.sayHi(msg);
    }

}
