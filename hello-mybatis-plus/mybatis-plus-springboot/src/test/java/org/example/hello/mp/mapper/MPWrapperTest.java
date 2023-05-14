package org.example.hello.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.hello.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jack.wen
 * @since 2023/5/14 15:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MPWrapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testQueryWrapper() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        Map<String, Object> params = new HashMap<>();
        params.put("name", "jack");
        params.put("age", "20");
        params.put("email", null);
//        wrapper.allEq(params); // SELECT id,name,age,email FROM user WHERE (name = ? AND age = ? AND email IS NULL)
//        wrapper.allEq(params, false); // SELECT id,name,age,email FROM user WHERE (name = ? AND age = ?)
        wrapper.allEq((k, v) -> (!k.equals("email")), params); // SELECT id,name,age,email FROM user WHERE (name = ? AND age = ?)

        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);
    }

    @Test
    public void testQueryWrapperCompare() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email", "test2@baomidou.com")
                .ge("age", 20)
                .in("name", "Jack", "Jone");
        // SELECT id,name,age,email FROM user WHERE (email = ? AND age >= ? AND name IN (?,?))
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);
    }

    @Test
    public void testQueryWrapperLike() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("name", "J");
        // SELECT id,name,age,email FROM user WHERE (name LIKE %J%)
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);
    }

    @Test
    public void testQueryWrapperSort() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("age");
        // SELECT id,name,age,email FROM user ORDER BY age DESC
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);
    }

    @Test
    public void testQueryWrapperJoin() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "Jack").or().eq("age", 20);
        // SELECT id,name,age,email FROM user WHERE (name = ? OR age = ?)
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);
    }

    @Test
    public void testQueryWrapperSelect() {
        User user = new User();
        user.setName("autofill");
        user.setAge(18);
        user.setEmail("autofill@baomidou.com");
        userMapper.insert(user);
        System.out.println(user);
        //
    }

}
