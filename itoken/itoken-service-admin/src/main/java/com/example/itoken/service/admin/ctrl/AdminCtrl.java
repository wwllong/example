package com.example.itoken.service.admin.ctrl;

import cn.hutool.core.util.StrUtil;
import com.example.itoken.common.dto.BaseResult;
import com.example.itoken.service.admin.domain.TbSysUser;
import com.example.itoken.service.admin.service.AdminService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class AdminCtrl {


    @Autowired
    private AdminService adminService;

    @GetMapping("login")
    public BaseResult login(String loginCode, String password){

        BaseResult result = checkLogin(loginCode, password);
        if (Objects.nonNull(result)){
            return result;
        }
        TbSysUser tbSysUser = adminService.login(loginCode, password);

        if (Objects.nonNull(tbSysUser)){
            return BaseResult.ok(tbSysUser);
        }else{
            return BaseResult.notOk(Lists.newArrayList(new BaseResult.Error(StrUtil.EMPTY, "登录失败")));
        }

    }

    /**
     * 登录校验
     * @param loginCode
     * @param password
     * @return
     */
    private BaseResult checkLogin(String loginCode, String password) {
        if (StrUtil.isAllNotBlank(loginCode, password)){
            return null;
        }

        List<BaseResult.Error> errorList = Lists.newArrayList();

        if (StrUtil.isBlank(loginCode)){
            errorList.add(new BaseResult.Error("loginCode", "登录账号不能为空"));
        }
        if (StrUtil.isBlank(password)){
            errorList.add(new BaseResult.Error("password", "密码不能为空"));
        }

        return BaseResult.notOk(errorList);
    }


}
