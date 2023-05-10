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
public class MPMapperCURDTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        User user = new User();
        user.setName("mp.insert");
        user.setAge(18);
        user.setEmail("mp.insert@baomidou.com");
        userMapper.insert(user);

        System.out.println(user);
        // User(id=1650130102197522434, name=mp.insert, age=18, email=mp.insert@baomidou.com)
        // 自增：User(id=6, name=mp.insert, age=18, email=mp.insert@baomidou.com)
    }

    @Test
    public void testUpdateById() {
        User user = new User();
        user.setId(6L);
        user.setName("mp.updateById");
        // 根据id更新，更新不为null的字段
        userMapper.updateById(user);

        System.out.println(userMapper.selectById(6L));
        // User(id=6, name=mp.updateById, age=18, email=mp.insert@baomidou.com)
    }

    @Test
    public void testUpdate() {
        // 1.使用QueryWrapper更新
        User user = new User();
        user.setName("mp.updateByCondition");
        user.setAge(20);

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", 6);
        userMapper.update(user, wrapper);

        System.out.println(userMapper.selectById(6L));
        // User(id=6, name=mp.updateByCondition, age=20, email=mp.insert@baomidou.com)

        // 2.或者使用UpdateWrapper更新
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", 6).set("name", "mp.updateByUpdateWrapper");
        userMapper.update(null, updateWrapper);
        System.out.println(userMapper.selectById(6L));
        // User(id=6, name=mp.updateByUpdateWrapper, age=20, email=mp.insert@baomidou.com)
    }

    @Test
    public void testDeleteById() {
        int i = userMapper.deleteById(6L);
        System.out.println(i); // 1
        System.out.println(userMapper.selectById(6L)); // null
    }

    @Test
    public void testDeleteByMap() {
        // 删除条件，默认and连接
        Map<String, Object> columnsMap = new HashMap<>();
        columnsMap.put("age", 18);
        columnsMap.put("name", "mp.insert");
        int i = userMapper.deleteByMap(columnsMap);
        System.out.println(i); // 1
        System.out.println(userMapper.selectById(7L)); // null
    }

    @Test
    public void testDeleteByWrapper() {
        // 将实体对象进⾏包装，包装为操作条件
        User user = new User();
        user.setAge(18);
        user.setName("mp.insert");

        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        int i = userMapper.delete(wrapper);
        System.out.println(i); // 1
    }

    @Test
    public void testDeleteByIds() {
        int i = userMapper.deleteBatchIds(Arrays.asList(9L, 10L, 11L));
        System.out.println(i); // 3
    }

    @Test
    public void testSelectById() {
        User user = userMapper.selectById(1L);
        System.out.println(user);
        // User(id=1, name=Jone, age=18, email=test1@baomidou.com)
    }

    @Test
    public void testSelectBatchIds() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));
        System.out.println(users);
        // [User(id=1, name=Jone, age=18, email=test1@baomidou.com), User(id=2, name=Jack, age=20, email=test2@baomidou.com), User(id=3, name=Tom, age=28, email=test3@baomidou.com)]
    }

    @Test
    public void testSelectOne() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "Jone");
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
        // User(id=1, name=Jone, age=18, email=test1@baomidou.com)
    }

    @Test
    public void testSelectCount() {
        // 年龄>=20
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("age", 20);
        Integer count = userMapper.selectCount(wrapper);
        System.out.println(count);
        // 3
    }

    @Test
    public void testSelectList() {
        // 年龄>=20
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("age", 20);
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);
        // [User(id=3, name=Tom, age=28, email=test3@baomidou.com), User(id=4, name=Sandy, age=21, email=test4@baomidou.com), User(id=5, name=Billie, age=24, email=test5@baomidou.com)]
    }

    @Test
    public void testSelectPage() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("age", 20);
        Page<User> page = new Page<>(1, 1);
        IPage<User> userPage = userMapper.selectPage(page, wrapper);
        System.out.println("数据总条数：" + userPage.getTotal());
        System.out.println("总⻚数：" + userPage.getPages());
        System.out.println(userPage.getRecords());
        // 数据总条数：3
        // 总⻚数：3
        // [User(id=3, name=Tom, age=28, email=test3@baomidou.com)]
    }


}
