package com.example.itoken.service.admin.ctrl;

import com.example.itoken.common.domain.TbSysUser;
import com.example.itoken.common.dto.BaseResult;
import com.example.itoken.service.admin.service.AdminService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/admins")
public class AdminCtrl {

    @Autowired
    private AdminService adminService;

    @GetMapping("page/{pageNum}/{pageSize}")
    public BaseResult page(@PathVariable int pageNum, @PathVariable int pageSize, @RequestParam(required = false) TbSysUser tbSysUser) {
        PageInfo<TbSysUser> page = adminService.page(pageNum, pageSize, tbSysUser);

        BaseResult.Cursor cursor = new BaseResult.Cursor();
        cursor.setTotal(Long.valueOf(page.getTotal()).intValue());
        cursor.setOffset(page.getPageNum());
        cursor.setLimit(page.getPageSize());

        return BaseResult.ok(page.getList(), cursor);
    }
}
