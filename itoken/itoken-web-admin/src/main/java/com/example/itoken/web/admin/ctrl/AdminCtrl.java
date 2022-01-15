package com.example.itoken.web.admin.ctrl;

import cn.hutool.core.util.StrUtil;
import com.example.itoken.common.dto.BaseResult;
import com.example.itoken.web.admin.client.AdminServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminCtrl {

    @GetMapping(value = {"", "index"})
    public String index(){
        return "index";
    }

}
