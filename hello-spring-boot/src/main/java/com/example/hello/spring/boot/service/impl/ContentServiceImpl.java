package com.example.hello.spring.boot.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.hello.spring.boot.mapper.ContentMapper;
import com.example.hello.spring.boot.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

    @Resource
    private ContentMapper contentMapper;

}

