package org.example.hello.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.hello.mybatis.entity.User;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author jack.wen
 * @version 1.0.0
 */
public class MyBatisTest {

    @Test
    public void testSelectAll() throws IOException {
        // 加载核⼼配置⽂件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 获得sqlSession⼯⼚对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 获得sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 执⾏sql语句
        List<User> userList = sqlSession.selectList("userMapper.findAll");
        // 打印结果
        System.out.println(userList);
        // 释放资源
        sqlSession.close();
    }

    @Test
    public void testAdd() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setUsername("jacky");
        user.setPassword("123456");
        // 执⾏sql语句
        int insert = sqlSession.insert("userMapper.add", user);
        System.out.println(insert);
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }

    @Test
    public void testUpdate() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setId(3);
        user.setUsername("jack");
        user.setPassword("1234567");
        // 执⾏sql语句
        int update = sqlSession.update("userMapper.update", user);
        System.out.println(update);
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }

    @Test
    public void testDeleted() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 执⾏sql语句
        int delete = sqlSession.update("userMapper.delete", 3);
        System.out.println(delete);
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }



}
