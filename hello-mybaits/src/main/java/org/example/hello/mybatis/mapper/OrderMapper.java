package org.example.hello.mybatis.mapper;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.example.hello.mybatis.entity.Order;
import org.example.hello.mybatis.entity.User;

import java.util.List;

/**
 * @author jack.wen
 * @since 2022/10/27 00:04
 */
public interface OrderMapper {

    @Select("select * from orders")
    @Results({
            @Result(id=true, property = "id", column = "id"),
            @Result(property = "orderNo",column = "order_no"),
            @Result(property = "amount",column = "amount"),
            @Result(property = "user", column = "uid", javaType = User.class,
                    one = @One(select = "org.example.hello.mybatis.mapper.UserMapper.findById"))
    })
    List<Order> findAll();

    @Select("select * from orders where uid = #{uid}")
    List<Order> findByUid(int uid);
}
