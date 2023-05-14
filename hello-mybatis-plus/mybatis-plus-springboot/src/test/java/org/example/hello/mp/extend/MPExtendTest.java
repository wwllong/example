package org.example.hello.mp.extend;

import org.example.hello.mp.entity.User;
import org.example.hello.mp.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author jack.wen
 * @since 2023/5/14 18:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MPExtendTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testTenant() {
        List<User> allUser = userMapper.findAll();
        System.out.println(allUser);
    }

    @Test
    public void testAutoFill() {
        User user = new User();
        user.setName("autofill");
        user.setAge(18);
        user.setEmail("autofill@baomidou.com");
        userMapper.insert(user);
        System.out.println(user);
        // User(id=18, name=autofill, age=18, email=autofill@baomidou.com, tenantId=null, version=null, createTime=2023-05-14T23:37:03.520, updateTime=null)
        user.setAge(19);
        userMapper.updateById(user);
        // User(id=18, name=autofill, age=19, email=autofill@baomidou.com, tenantId=null, version=null, createTime=2023-05-14T23:37:03.520, updateTime=2023-05-14T23:37:03.837)
        System.out.println(user);
    }

    @Test
    public void testLogicDeleted() {
        userMapper.deleteById(1L);
        // UPDATE user SET deleted=1 WHERE id=1 AND deleted=0
        User user = userMapper.selectById(1L);
        // SELECT id,name,age,email,tenant_id,version,create_time,update_time,deleted FROM user WHERE id=1 AND deleted=0
        Assert.assertNull(user);
    }
}
