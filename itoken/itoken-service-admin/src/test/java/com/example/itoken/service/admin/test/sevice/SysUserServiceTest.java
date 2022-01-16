package com.example.itoken.service.admin.test.sevice;

import com.example.itoken.common.domain.TbSysUser;
import com.example.itoken.service.admin.ServiceAdminApplication;
import com.example.itoken.service.admin.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceAdminApplication.class)
@ActiveProfiles(value = "dev")
@Transactional
@Rollback
public class SysUserServiceTest {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 注册
     */
    @Test
    public void testRegister() {
        TbSysUser tbSysUser = new TbSysUser();
        tbSysUser.setUserCode(UUID.randomUUID().toString());
        tbSysUser.setLoginCode("wenwl@163.com");
        tbSysUser.setUserName("wenwl");
        tbSysUser.setUserType("1");
        tbSysUser.setMgrType("1");
        tbSysUser.setStatus("0");
        tbSysUser.setCreateBy(tbSysUser.getUserCode());
        tbSysUser.setCreateDate(new Date());
        tbSysUser.setUpdateBy(tbSysUser.getUserCode());
        tbSysUser.setUpdateDate(new Date());
        tbSysUser.setCorpCode("0");
        tbSysUser.setCorpName("iToken");
        tbSysUser.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        sysUserService.register(tbSysUser);
    }

    /**
     * 登录
     */
    @Test
    public void testLogin() {
//        TbSysUser tbSysUser = adminService.login("wenwl@163.com", "123456");
//        Assert.assertNotNull(tbSysUser);
    }

}
