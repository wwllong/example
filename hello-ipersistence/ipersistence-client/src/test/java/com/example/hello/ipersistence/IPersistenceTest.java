package com.example.hello.ipersistence;

import com.example.hello.ipersistence.io.Resources;
import com.example.hello.ipersistence.pojo.entity.User;
import com.example.hello.ipersistence.session.SqlSession;
import com.example.hello.ipersistence.session.SqlSessionFactory;
import com.example.hello.ipersistence.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @author jack.wen
 * @version 1.0.0
 * @date 2022/8/17 01:17
 */
public class IPersistenceTest {

    private SqlSession sqlSession;

    @Before
    public void before() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        sqlSession = sqlSessionFactory.openSession();
    }

    @Test
    public void testSelectList() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("jack");
        List<User> userList = sqlSession.selectList("user.selectList");
        for (User user1 : userList) {
            System.out.println(user1);
        }
        sqlSession.close();
    }

    @Test
    public void testSelectOne() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("jack");
        User user2 = sqlSession.selectOne("user.selectOne", user);
        System.out.println(user2);
        sqlSession.close();
    }

}
