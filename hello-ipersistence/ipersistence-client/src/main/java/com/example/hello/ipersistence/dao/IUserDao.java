package com.example.hello.ipersistence.dao;

import com.example.hello.ipersistence.pojo.entity.User;

import java.util.List;

/**
 * @author jack.wen
 * @version 1.0.0
 */
public interface IUserDao {

    List<User> selectList() throws Exception;

    User selectOne(User user) throws Exception;

}
