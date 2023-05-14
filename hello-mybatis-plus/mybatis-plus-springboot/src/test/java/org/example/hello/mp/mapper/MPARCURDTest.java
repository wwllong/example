package org.example.hello.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.hello.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jack.wen
 * @since 2023/4/22 23:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MPARCURDTest {

    @Test
    public void testAR() {
        User user = new User();
        user.setName("ar.insert");
        user.setAge(18);
        user.setEmail("mp.insert@baomidou.com");

        // insert
        user.insert();
        // select all
        List<User> users = user.selectAll();
        // selectById
        user.selectById(1L);

        // update
        User updateUser = new User();
        updateUser.setName("mp.updateByConditionAR");
        updateUser.setAge(20);

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", 6);
        updateUser.update(wrapper);
        // ...mp的curd方法都支持
    }


}
