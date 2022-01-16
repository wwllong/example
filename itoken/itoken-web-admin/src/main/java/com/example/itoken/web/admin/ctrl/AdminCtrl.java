package com.example.itoken.web.admin.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminCtrl {

    @GetMapping(value = {"", "index"})
    public String index(){
        return "index";
    }

}
