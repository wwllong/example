package org.example.hello.mp.mapper;

import org.example.hello.mp.entity.User;

import java.util.List;

/**
 * @author jack.wen
 * @since 2023/4/18 10:15
 */
public interface UserMapper {
    List<User> findAll();
}
