package com.example.itoken.service.admin.service.impl;

import com.example.itoken.common.domain.TbSysUser;
import com.example.itoken.common.mapper.TbSysUserMapper;
import com.example.itoken.common.service.impl.BaseServiceImpl;
import com.example.itoken.service.admin.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AdminServiceImpl extends BaseServiceImpl<TbSysUser, TbSysUserMapper> implements AdminService<TbSysUser> {

    @Override
    @Transactional(readOnly = false)
    public void register(TbSysUser tbSysUser) {
        insert(tbSysUser, "creator");
    }

}
