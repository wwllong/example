package com.example.itoken.common.service.impl;

import cn.hutool.core.date.DateUtil;
import com.example.itoken.common.domain.BaseDomain;
import com.example.itoken.common.service.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.MyMapper;

@Service
@Transactional(readOnly = true)
public class BaseServiceImpl<T extends BaseDomain, D extends MyMapper<T>> implements BaseService<T> {

    @Qualifier
    private D baseMapper;

    @Override
    @Transactional(readOnly = false)
    public int insert(T t, String createBy) {
        t.setCreateBy(createBy);
        t.setCreateDate(DateUtil.date());
        return baseMapper.insert(t);
    }

    @Override
    @Transactional(readOnly = false)
    public int delete(T t) {
        return baseMapper.delete(t);
    }

    @Override
    public int count(T t) {
        return baseMapper.selectCount(t);
    }

    @Override
    public T findOne(T t) {
        return baseMapper.selectOne(t);
    }

    @Override
    public PageInfo<T> page(int pageNum, int pageSize, T t) {
        PageHelper.startPage(pageNum, pageSize);

        return new PageInfo<>(baseMapper.select(t));
    }

    @Override
    @Transactional(readOnly = false)
    public int update(T t, String updateBy) {
        t.setUpdateBy(updateBy);
        t.setUpdateDate(DateUtil.date());
        return baseMapper.updateByPrimaryKey(t);
    }

}
