package com.example.itoken.service.admin.ctrl;

import cn.hutool.core.util.StrUtil;
import com.example.itoken.common.ctrl.BaseController;
import com.example.itoken.common.domain.TbSysUser;
import com.example.itoken.common.dto.BaseResult;
import com.example.itoken.service.admin.service.SysUserService;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@RestController
@RequestMapping("v1/admins")
public class AdminCtrl extends BaseController<SysUserService, TbSysUser> {

    @Override
    @PostMapping("page/{pageNum}/{pageSize}")
    public BaseResult<List<TbSysUser>> page(@PathVariable(value = "pageNum") Integer pageNum,
                                            @PathVariable(value = "pageSize") Integer pageSize,
                                            @RequestBody TbSysUser domain) {
        Example example = new Example(domain.getClass());
        Example.Criteria criteria = example.createCriteria();
        if (StrUtil.isNotBlank(domain.getUserName())) {
            criteria.andLike("userName", domain.getUserName());
        }
        if (StrUtil.isNotBlank(domain.getEmail())) {
            criteria.andLike("email", domain.getEmail());
        }
        if (StrUtil.isNotBlank(domain.getPhone())) {
            criteria.andLike("phone", domain.getPhone());
        }
        return page(pageNum, pageSize, example);
    }

    @PostMapping()
    @Override
    public BaseResult<Integer> save(@RequestBody TbSysUser domain) {
        // 密码加密
        String password = DigestUtils.md5DigestAsHex(domain.getPassword().getBytes());
        domain.setPassword(password);
        domain.setCreateBy(domain.getUserName());
        domain.setUserType(StrUtil.blankToDefault(domain.getUserType(), "1"));
        domain.setMgrType(StrUtil.blankToDefault(domain.getMgrType(), "0"));
        domain.setStatus(StrUtil.blankToDefault(domain.getStatus(), "1"));
        domain.setCorpCode(StrUtil.blankToDefault(domain.getCorpCode(), "0"));
        domain.setCorpName(StrUtil.blankToDefault(domain.getCorpName(), "iToken"));
        return super.save(domain);
    }


}
