package com.example.itoken.service.redis.service;


public interface RedisService {

    void set(String key, String value, long seconds);

    String get(String key);

    void remove(String key);

}
