package org.example.hello.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.hello.mybatis.entity.Order;
import org.example.hello.mybatis.entity.User;
import org.example.hello.mybatis.mapper.OrderMapper;
import org.example.hello.mybatis.mapper.RoleMapper;
import org.example.hello.mybatis.mapper.UserMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author jack.wen
 * @version 1.0.0
 */
public class MyBatisAnnotationTest {

    private UserMapper userMapper;
    private SqlSession sqlSession;

    private OrderMapper orderMapper;

    @Before
    public void before() throws IOException {
        // 加载核⼼配置⽂件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 获得sqlSession⼯⼚对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 获得sqlSession对象
        sqlSession = sqlSessionFactory.openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
        orderMapper = sqlSession.getMapper(OrderMapper.class);
    }

    @After
    public void after() {
        // 释放资源
        sqlSession.close();
    }

    @Test
    public void testSelectAll() {
        // 执⾏sql语句
        List<User> userList = userMapper.findAll();
        // 打印结果
        System.out.println(userList);
    }

    @Test
    public void testAdd() {
        User user = new User();
        user.setUsername("jacky_2");
        user.setPassword("123456");
        // 执⾏sql语句
        int insert = userMapper.add(user);
        System.out.println(insert);
        sqlSession.commit();
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(4);
        user.setUsername("jacky_2");
        user.setPassword("1234567");
        // 执⾏sql语句
        int update = userMapper.update( user);
        System.out.println(update);
        sqlSession.commit();
    }

    @Test
    public void testDeleted() {
        int delete = userMapper.deleteById(4);
        System.out.println(delete);
        sqlSession.commit();
    }


    @Test
    public void testOne2One() {
        List<Order> all = orderMapper.findAll();
        for(Order order : all){
            System.out.println(order);
        }
    }

    @Test
    public void testOne2Many() {
        List<User> all = userMapper.findAllWithOrders();
        for(User usr : all){
            System.out.println(usr);
        }
    }

    @Test
    public void testMany2Many() {
        List<User> all = userMapper.findAllWithRoles();
        for(User usr : all){
            System.out.println(usr);
        }
    }
}
