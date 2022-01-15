package com.example.itoken.service.redis.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.example.itoken.service.redis.service.RedisService;
import io.lettuce.core.RedisURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void set(String key, Object value, long seconds) {
        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }

    @Override
    public String get(String key) {
        Object obj = redisTemplate.opsForValue().get(key);
        return ObjectUtil.isNotNull(obj) ? JSONUtil.toJsonStr(obj) : null;
    }

    @Override
    public void remove(String key) {
        redisTemplate.delete(key);
    }

}
