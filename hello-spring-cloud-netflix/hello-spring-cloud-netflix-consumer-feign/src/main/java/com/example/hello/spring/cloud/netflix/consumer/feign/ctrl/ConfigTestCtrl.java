package com.example.hello.spring.cloud.netflix.consumer.feign.ctrl;

import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class ConfigTestCtrl {

    @GetMapping(value = "hello")
    public String sayHello() {
        DynamicStringProperty property = DynamicPropertyFactory.getInstance().getStringProperty("user.name", "Hello Config");
        return property.getValue();
    }

}
