package com.example.hello.spring.boot.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.hello.spring.boot.mapper.ItemParamMapper;
import com.example.hello.spring.boot.service.ItemParamService;

@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Resource
    private ItemParamMapper itemParamMapper;

}

