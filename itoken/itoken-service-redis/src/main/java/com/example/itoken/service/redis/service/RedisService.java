package com.example.itoken.service.redis.service;


public interface RedisService {

    void set(String key, Object value, long seconds);

    String get(String key);

}
