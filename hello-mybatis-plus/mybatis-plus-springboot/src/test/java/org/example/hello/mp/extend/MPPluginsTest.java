package org.example.hello.mp.extend;

import org.example.hello.mp.entity.User;
import org.example.hello.mp.mapper.UserMapper;
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
public class MPPluginsTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testTenant() {
        List<User> allUser = userMapper.selectList(null);
        // SELECT tenant_id, id, name, age, email FROM user WHERE tenant_id = 1
        System.out.println(allUser);
    }

    @Test
    public void testUpdateVersion() {
        // SELECT id, name, age, email, tenant_id, version FROM user WHERE id = 2 AND tenant_id = 1
        User user = userMapper.selectById(2L);
        user.setName("Jack2");
        // 查询出来的user的version必须非空，不然不会+1
        // UPDATE user SET name = 'Jack2', age = 20, email = 'test2@baomidou.com', tenant_id = 1, version = 2 WHERE tenant_id = 1 AND id = 2 AND version = 1
        int i = userMapper.updateById(user);
        // 1
        System.out.println(i);
    }

    @Test
    public void testIllegalSQL() {
        User user = new User();
        user.setName("illegal test");
        userMapper.update(user, null);
    }

    @Test
    public void testDelAndUpdateAll() {
        User user = new User();
        user.setName("illegal test");
        userMapper.update(user, null);
        // Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Prohibition of table update operation
        userMapper.delete(null);
        // Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Prohibition of full table deletion
    }
}
