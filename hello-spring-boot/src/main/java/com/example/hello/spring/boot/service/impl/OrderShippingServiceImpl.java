package com.example.hello.spring.boot.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.hello.spring.boot.mapper.OrderShippingMapper;
import com.example.hello.spring.boot.service.OrderShippingService;

@Service
public class OrderShippingServiceImpl implements OrderShippingService {

    @Resource
    private OrderShippingMapper orderShippingMapper;

}

