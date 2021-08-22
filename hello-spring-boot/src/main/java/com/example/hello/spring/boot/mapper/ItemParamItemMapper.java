package com.example.hello.spring.boot.mapper;

import com.example.hello.spring.boot.domain.ItemParamItem;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.MyMapper;

@Mapper
public interface ItemParamItemMapper extends MyMapper<ItemParamItem> {
}