package com.example.itoken.web.admin.ctrl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.example.itoken.common.domain.TbSysUser;
import com.example.itoken.common.dto.BaseResult;
import com.example.itoken.common.web.ctrl.BaseController;
import com.example.itoken.service.api.client.AdminServiceClient;
import com.example.itoken.web.admin.service.UserBizService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("user")
public class UserCtrl extends BaseController<AdminServiceClient, TbSysUser> {

    @Autowired
    private UserBizService userBizService;

    /**
     * ModelAttribute-创建实体对象
     *
     * @return
     */
    public TbSysUser createEntity() {
        return new TbSysUser();
    }

    @PostMapping(value = "save")
    public String save(TbSysUser tbUser, RedirectAttributes redirectAttributes, Model model){
        BaseResult<Integer> baseResult = baseClient.save(tbUser);
        if(baseResult.success()){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/user/list";
        }else{
            model.addAttribute("baseResult", baseResult);
            return "form";
        }
    }
}
