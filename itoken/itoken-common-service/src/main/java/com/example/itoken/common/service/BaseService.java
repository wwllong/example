package com.example.itoken.common.service;

import com.example.itoken.common.domain.BaseDomain;
import com.example.itoken.common.dto.BaseResult;
import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;

import java.util.Set;

public interface BaseService<T extends BaseDomain> {

    int insert(T t, String createBy);

    int delete(T t);

    int deleteByPrimaryKeys(Set<Object> ids);

    int count(T t);

    T findOne(T t);

    T findByPrimaryKey(Object id);

    PageInfo<T> page(int pageNum, int pageSize, Example example);

    int update(T t, String updateBy);

    int save(T domain);

}
