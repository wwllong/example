package org.example.hello.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.hello.mybatis.entity.User;
import org.example.hello.mybatis.mapper.OrderMapper;
import org.example.hello.mybatis.mapper.UserMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author jack.wen
 * @since 2022/11/7 23:19
 */
public class MyBatisCacheTest {

    private UserMapper userMapper;
    private SqlSession sqlSession;
    SqlSessionFactory sqlSessionFactory;


    @Before
    public void before() throws IOException {
        // 加载核⼼配置⽂件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 获得sqlSession⼯⼚对象
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 获得sqlSession对象
        sqlSession = sqlSessionFactory.openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @After
    public void after() {
        // 释放资源
        sqlSession.close();
    }

    @Test
    public void test1() {
        // 第一次查询
        User user1 = userMapper.findById(1);
        System.out.println(user1);
        // 第二次查询
        User user2 = userMapper.findById(1);
        System.out.println(user2);
    }

    @Test
    public void test2() {
        // 第一次查询
        User user1 = userMapper.findById(1);
        System.out.println(user1);
        user1.setUsername("jack2");
        userMapper.update(user1);
        sqlSession.commit(); // 提交事务
        // 第二次查询
        User user2 = userMapper.findById(1);
        System.out.println(user2);
    }

    @Test
    public void testTwoCache() {
        // 根据sqlSessionFactory 产生 session
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        // 第一次查询，发出sql语句，并将查询的结果放入缓存中
        User u1 = userMapper1.findById(1);
        System.out.println(u1);
        // 第一次查询完后关闭 sqlSession
        sqlSession1.close();
        // 第二次查询，即使sqlSession1已经关闭了，这次查询依然不发出sql语句
        User u2 = userMapper2.findById(1);
        System.out.println(u2);
        sqlSession2.close();
    }

    @Test
    public void testTwoCache2() {
        // 根据sqlSessionFactory 产生 session
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        SqlSession sqlSession3 = sqlSessionFactory.openSession();
        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        UserMapper userMapper3 = sqlSession3.getMapper(UserMapper.class);
        // 第一次查询，发出sql语句，并将查询的结果放入缓存中
        User u1 = userMapper1.findById(1);
        System.out.println(u1);
        // 第一次查询完后关闭 sqlSession
        sqlSession1.close();
        // 执行更新操作，commit()
        u1.setUsername( "jack3" );
        userMapper3.update(u1);
        sqlSession3.commit();
        // 第二次查询，由于上次更新操作，缓存数据已经清空(防止数据脏读)，这里必须再次发出sql语句
        User u2 = userMapper2.findById(1);
        System.out.println(u2);
        sqlSession2.close();
    }

    @Test
    public void testTwoCache4Redis() {
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        SqlSession sqlSession3 = sqlSessionFactory.openSession();
        UserMapper mapper1 = sqlSession1.getMapper(UserMapper.class);
        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
        UserMapper mapper3 = sqlSession3.getMapper(UserMapper.class);

        User user1 = mapper1.findById(1);
        System.out.println(user1);
        sqlSession1.close();
        // 清空缓存
        User user = new User();
        user.setId(1);
        user.setUsername("jack4");
        mapper3.update(user);
        sqlSession3.commit();

        User user2 = mapper2.findById(1);
        System.out.println(user2);
        sqlSession2.close();
    }

}
