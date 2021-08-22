package com.example.hello.spring.boot.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.hello.spring.boot.mapper.ItemDescMapper;
import com.example.hello.spring.boot.service.ItemDescService;

@Service
public class ItemDescServiceImpl implements ItemDescService {

    @Resource
    private ItemDescMapper itemDescMapper;

}

