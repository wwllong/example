package org.example.hello.mybatis.mapper;

import org.apache.ibatis.annotations.*;
import org.example.hello.mybatis.entity.Order;
import org.example.hello.mybatis.entity.User;

import java.util.List;

/**
 * @author jack.wen
 * @since 2022/10/27 00:04
 */
@CacheNamespace(blocking = true, implementation = org.mybatis.caches.redis.RedisCache.class)
public interface UserMapper {

//    传统复杂开发
//    List<User> findAll();
//    List<User> findAllWithRole();

//  注解开发
    @Select("select * from User")
    List<User> findAll();
    @Insert("insert into user(username, password) values(#{username}, #{password})")
    Integer add(User user);
    @Update("update user set username = #{username}, password = #{password} where id = #{id}")
    Integer update(User user);
    @Delete("delete from user where id = #{id}")
    Integer deleteById(Integer id);

    @Select("select * from user where id = #{id}")
    @Options(useCache = true)
    User findById(int id);
    @Select("select * from User")
    @Results({
            @Result(id = true,property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "orderList", column = "id", javaType = List.class,
                    many = @Many(select = "org.example.hello.mybatis.mapper.OrderMapper.findByUid"))
    })
    List<User> findAllWithOrders();

    @Select("select * from User")
    @Results({
            @Result(id = true,property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "roleList", column = "id", javaType = List.class,
                    many = @Many(select = "org.example.hello.mybatis.mapper.RoleMapper.findByUid"))
    })
    List<User> findAllWithRoles();

}
