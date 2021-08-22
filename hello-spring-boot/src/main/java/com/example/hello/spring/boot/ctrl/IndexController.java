package com.example.hello.spring.boot.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(value = "thymeleaf/index")
    public String index(Model model) {
        model.addAttribute("name", "张三");
        return "index";
    }
}