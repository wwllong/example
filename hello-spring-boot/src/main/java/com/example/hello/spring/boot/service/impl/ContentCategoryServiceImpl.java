package com.example.hello.spring.boot.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.hello.spring.boot.mapper.ContentCategoryMapper;
import com.example.hello.spring.boot.service.ContentCategoryService;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Resource
    private ContentCategoryMapper contentCategoryMapper;

}

