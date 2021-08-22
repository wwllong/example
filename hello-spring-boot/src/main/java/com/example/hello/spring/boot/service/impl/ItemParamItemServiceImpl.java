package com.example.hello.spring.boot.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.hello.spring.boot.mapper.ItemParamItemMapper;
import com.example.hello.spring.boot.service.ItemParamItemService;

@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {

    @Resource
    private ItemParamItemMapper itemParamItemMapper;

}

