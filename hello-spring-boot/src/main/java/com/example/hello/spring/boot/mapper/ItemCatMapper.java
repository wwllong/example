package com.example.hello.spring.boot.mapper;

import com.example.hello.spring.boot.domain.ItemCat;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.MyMapper;

@Mapper
public interface ItemCatMapper extends MyMapper<ItemCat> {
}