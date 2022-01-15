package com.example.itoken.service.admin.mapper;

import com.example.itoken.common.domain.TbSysUser;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.MyMapper;

@Mapper
public interface TbSysUserExtendMapper extends MyMapper<TbSysUser> {
}