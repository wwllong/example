package com.example.itoken.service.redis.ctrl;

import cn.hutool.core.util.ObjectUtil;
import com.example.itoken.common.dto.BaseResult;
import com.example.itoken.service.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RedisCtrl {

    @Autowired
    private RedisService redisService;

    @PostMapping("set")
    public BaseResult<Boolean> set(@RequestParam("key") String key,
                                   @RequestParam("value") String value,
                                   @RequestParam("seconds") Long seconds) {
        redisService.set(key, value, seconds);
        return BaseResult.ok();
    }

    @GetMapping("find")
    public BaseResult<String> find(@RequestParam("key") String key) {
        return BaseResult.ok(redisService.get(key));
    }

    @DeleteMapping("remove")
    public BaseResult<Boolean> remove(@RequestParam("key") String key) {
        redisService.remove(key);
        return BaseResult.ok();
    }

}
