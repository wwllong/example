package com.example.itoken.service.redis.ctrl;

import cn.hutool.core.util.ObjectUtil;
import com.example.itoken.service.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisCtrl {

    @Autowired
    private RedisService redisService;

    @PostMapping("set")
    public Boolean set(String key, String value, long seconds) {
        redisService.set(key, value, ObjectUtil.defaultIfNull(seconds, 300L));
        return Boolean.TRUE;
    }

    @GetMapping("find")
    public String find(String key) {
        return redisService.get(key);
    }
}
