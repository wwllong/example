package com.example.itoken.service.admin.service;


import com.example.itoken.common.domain.BaseDomain;
import com.example.itoken.common.domain.TbSysUser;
import com.example.itoken.common.service.BaseService;

public interface AdminService<T extends BaseDomain> extends BaseService<T> {

    /**
     * 注册
     * @param tbSysUser
     */
    void register(TbSysUser tbSysUser);

}
