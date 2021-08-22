package com.example.hello.spring.boot;

import com.example.hello.spring.boot.domain.User;
import com.example.hello.spring.boot.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloSpringBootApplication.class)
@Transactional
@Rollback
public class MyBatisTests {

    /**
     * 注入数据查询接口
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * 测试插入数据
     */
    @Test
    public void testInsert() {
        User User = new User();
        User.setUsername("wenwl");
        User.setPassword("123456");
        User.setPhone("15888888888");
        User.setEmail("wwllong18@163.com");
        User.setCreated(new Date());
        User.setUpdated(new Date());

        userMapper.insert(User);
    }

    /**
     * 测试删除数据
     */
    @Test
    public void testDelete() {
        // 构造条件
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", "wenwl");

        userMapper.deleteByExample(example);
    }

    /**
     * 测试修改数据
     */
    @Test
    public void testUpdate() {
        // 构造条件
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", "wenwl");

        // 构造一条测试数据
        User user = new User();
        user.setUsername("wenwlNew");
        user.setCreated(new Date());
        user.setUpdated(new Date());

        // 修改数据
        userMapper.updateByExample(user, example);
    }

    /**
     * 测试查询集合
     */
    @Test
    public void testSelect() {
        List<User> users = userMapper.selectAll();
        for (User user : users) {
            System.out.println(user.getUsername());
        }
    }

    /**
     * 测试分页查询
     */
    @Test
    public void testPage() {
        // PageHelper 使用非常简单，只需要设置页码和每页显示笔数即可
        PageHelper.startPage(0, 5);

        // 设置分页查询条件
        Example example = new Example(User.class);
        PageInfo<User> pageInfo = new PageInfo<>(userMapper.selectByExample(example));

        // 获取查询结果
        List<User> Users = pageInfo.getList();
        for (User User : Users) {
            System.out.println(User.getUsername());
        }
    }
}