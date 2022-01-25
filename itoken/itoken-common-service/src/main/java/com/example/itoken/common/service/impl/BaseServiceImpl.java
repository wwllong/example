package com.example.itoken.common.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.example.itoken.common.domain.BaseDomain;
import com.example.itoken.common.service.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.MyMapper;
import tk.mybatis.mapper.entity.Example;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Set;

@Transactional(readOnly = true)
public class BaseServiceImpl<T extends BaseDomain, D extends MyMapper<T>> implements BaseService<T> {

    @Autowired
    private D baseMapper;

    @Override
    @Transactional(readOnly = false)
    public int insert(T t, String createBy) {
        t.setCreateBy(createBy);
        t.setCreateDate(DateUtil.date());
        t.setUpdateBy(createBy);
        t.setUpdateDate(DateUtil.date());
        return baseMapper.insert(t);
    }

    @Override
    @Transactional(readOnly = false)
    public int delete(T t) {
        return baseMapper.delete(t);
    }

    @Override
    @Transactional(readOnly = false)
    public int deleteByPrimaryKeys(Set<Object> ids) {
        if (ArrayUtil.isNotEmpty(ids)) {
            for (Object id : ids) {
                baseMapper.deleteByPrimaryKey(id);
            }
            return ids.size();
        }
        return 0;
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
    public T findByPrimaryKey(Object id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<T> page(int pageNum, int pageSize, Example example) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(baseMapper.selectByExample(example));
    }

    @Override
    @Transactional(readOnly = false)
    public int update(T t, String updateBy) {
        t.setUpdateBy(updateBy);
        t.setUpdateDate(DateUtil.date());
        return baseMapper.updateByPrimaryKeySelective(t);
    }

    /**
     * 保存
     * @param domain
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public int save(T domain) {
        if (Objects.isNull(domain)) {
            return 0;
        }
        // 新增
        if(setPrimaryKey(domain)) {
            Assert.notNull(domain.getCreateBy(), "createBy can not be null");
            return insert(domain, domain.getCreateBy());
        }
        // 编辑
        else{
            Assert.notNull(domain.getCreateBy(), "updateBy can not be null");
            return update(domain, domain.getUpdateBy());
        }
    }

    private boolean setPrimaryKey(T domain) {
        boolean isSetPrimaryKey = false;
        // 通过反射找primaryKey（通常数据库设计,建议有通用的primaryKey）
        Field[] fields = ReflectUtil.getFields(domain.getClass());
        Field primaryKeyField = null;
        for (Field field : fields) {
            Id idAnnotation = field.getAnnotation(Id.class);
            if (Objects.nonNull(idAnnotation)) {
                primaryKeyField = field;
                break;
            }
        }
        Assert.notNull(primaryKeyField, "domain cannot be without primaryKeyField");
        Object primaryKeyValue = ReflectUtil.getFieldValue(domain, primaryKeyField);
        boolean isStrField = primaryKeyField.getType() == String.class;
        // 新增
        if(Objects.isNull(primaryKeyValue) || (isStrField && StrUtil.isBlank(String.valueOf(primaryKeyValue)))){
            ReflectUtil.setFieldValue(domain, primaryKeyField, isStrField ? IdUtil.randomUUID() : IdUtil.getSnowflake(1,1).nextIdStr());
            isSetPrimaryKey = true;
        }
        return isSetPrimaryKey;
    }



}
