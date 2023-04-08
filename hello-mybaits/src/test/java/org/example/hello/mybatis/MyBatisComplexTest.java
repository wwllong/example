package org.example.hello.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.hello.mybatis.entity.Order;
import org.example.hello.mybatis.entity.User;
import org.example.hello.mybatis.mapper.OrderMapper;
import org.example.hello.mybatis.mapper.UserMapper;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author jack.wen
 * @since 2022/10/27 00:14
 */
public class MyBatisComplexTest {

    @Test
    public void testOne2One() throws IOException {
        // 加载核⼼配置⽂件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 获得sqlSession⼯⼚对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 获得sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 执⾏sql语句
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        List<Order> all = mapper.findAll();
        for(Order order : all){
            System.out.println(order);
        }
        // 释放资源
        sqlSession.close();

    }

    @Test
    public void testOne2Many() throws IOException {
        // 加载核⼼配置⽂件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 获得sqlSession⼯⼚对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 获得sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 执⾏sql语句
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> all = mapper.findAll();
        for(User usr : all){
            System.out.println(usr);
        }
        // 释放资源
        sqlSession.close();

    }

    @Test
    public void testMany2Many() throws IOException {
//        // 加载核⼼配置⽂件
//        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
//        // 获得sqlSession⼯⼚对象
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
//        // 获得sqlSession对象
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        // 执⾏sql语句
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        List<User> all = mapper.findAllWithRole();
//        for(User usr : all){
//            System.out.println(usr);
//        }
//        // 释放资源
//        sqlSession.close();
    }

//    @Test
//    public void testOne2ManyLazy() throws IOException {
//        // 加载核⼼配置⽂件
//        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
//        // 获得sqlSession⼯⼚对象
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
//        // 获得sqlSession对象
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        // 执⾏sql语句
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        List<User> all = mapper.findAllLazy();
//        for(User usr : all){
//            System.out.println(usr.getId());
//            System.out.println(usr.getOrderList());
//        }
//        // 释放资源
//        sqlSession.close();
//    }

}
