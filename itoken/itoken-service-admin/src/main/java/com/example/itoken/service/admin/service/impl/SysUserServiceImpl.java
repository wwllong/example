package com.example.itoken.service.admin.service.impl;

import com.example.itoken.common.domain.TbSysUser;
import com.example.itoken.common.mapper.TbSysUserMapper;
import com.example.itoken.common.service.impl.BaseServiceImpl;
import com.example.itoken.service.admin.service.SysUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SysUserServiceImpl extends BaseServiceImpl<TbSysUser, TbSysUserMapper> implements SysUserService {

    @Override
    @Transactional(readOnly = false)
    public void register(TbSysUser tbSysUser) {
        insert(tbSysUser, "creator");
    }

}
