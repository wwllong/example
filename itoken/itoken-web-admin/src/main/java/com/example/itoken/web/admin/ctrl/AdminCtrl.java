package com.example.itoken.web.admin.ctrl;

import cn.hutool.core.util.StrUtil;
import com.example.itoken.common.dto.BaseResult;
import com.example.itoken.web.admin.client.AdminServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminCtrl {

    @Autowired
    private AdminServiceClient adminServiceClient;

    @GetMapping(value = {"", "login"})
    public String login(){
        BaseResult baseResult = adminServiceClient.login("wenwl@163.com", "123456");
        System.out.println(baseResult);
        return "index";
    }
}
