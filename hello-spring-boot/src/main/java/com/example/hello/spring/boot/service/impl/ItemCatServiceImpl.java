package com.example.hello.spring.boot.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.hello.spring.boot.mapper.ItemCatMapper;
import com.example.hello.spring.boot.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Resource
    private ItemCatMapper itemCatMapper;

}

