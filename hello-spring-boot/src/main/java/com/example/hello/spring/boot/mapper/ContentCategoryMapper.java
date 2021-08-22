package com.example.hello.spring.boot.mapper;

import com.example.hello.spring.boot.domain.ContentCategory;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.MyMapper;

@Mapper
public interface ContentCategoryMapper extends MyMapper<ContentCategory> {
}