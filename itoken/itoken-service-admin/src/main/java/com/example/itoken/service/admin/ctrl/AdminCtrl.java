package com.example.itoken.service.admin.ctrl;

import com.example.itoken.common.domain.TbSysUser;
import com.example.itoken.common.dto.BaseResult;
import com.example.itoken.common.dto.BaseResultPlus;
import com.example.itoken.service.admin.service.SysUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/admins")
public class AdminCtrl {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("page/{pageNum}/{pageSize}")
    public BaseResult<List<TbSysUser>> page(@PathVariable int pageNum, @PathVariable int pageSize, @RequestParam(required = false) TbSysUser tbSysUser) {
        PageInfo<TbSysUser> page = sysUserService.page(pageNum, pageSize, tbSysUser);
        return BaseResultPlus.ok(page);
    }

}
