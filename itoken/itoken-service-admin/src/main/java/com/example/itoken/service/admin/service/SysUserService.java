package com.example.itoken.service.admin.service;


import com.example.itoken.common.domain.TbSysUser;
import com.example.itoken.common.service.BaseService;

public interface SysUserService extends BaseService<TbSysUser> {

    /**
     * 注册
     * @param tbSysUser
     */
    void register(TbSysUser tbSysUser);

}
