//package com.example.hello.ipersistence.dao;
//
//import com.example.hello.ipersistence.io.Resources;
//import com.example.hello.ipersistence.pojo.entity.User;
//import com.example.hello.ipersistence.session.SqlSession;
//import com.example.hello.ipersistence.session.SqlSessionFactory;
//import com.example.hello.ipersistence.session.SqlSessionFactoryBuilder;
//
//import java.io.InputStream;
//import java.util.List;
//
///**
// * @author jack.wen
// * @version 1.0.0
// */
//public class UserDaoImpl implements IUserDao {
//    @Override
//    public List<User> selectList() throws Exception {
//
//        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        User user = new User();
//        user.setId(1);
//        user.setUsername("jack");
//        List<User> userList = sqlSession.selectList("user.selectList");
//        for (User user1 : userList) {
//            System.out.println(user1);
//        }
//        sqlSession.close();
//        return userList;
//
//    }
//
//    @Override
//    public User selectOne(User user) throws Exception {
//        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        User user2 = sqlSession.selectOne("user.selectOne", user);
//        System.out.println(user2);
//        sqlSession.close();
//        return user;
//    }
//}
