package com.example.itoken.common.service;

import com.example.itoken.common.domain.BaseDomain;
import com.github.pagehelper.PageInfo;

public interface BaseService<T extends BaseDomain> {

    int insert(T t, String createBy);

    int delete(T t);

    int count(T t);

    T findOne(T t);

    PageInfo<T> page(int pageNum, int pageSize, T t);

    int update(T t, String updateBy);

}
