package org.example.hello.mybatis.mapper;

import org.apache.ibatis.annotations.Select;
import org.example.hello.mybatis.entity.Role;

import java.util.List;

/**
 * @author jack.wen
 * @since 2022/11/6 23:18
 */
public interface RoleMapper {

    @Select("select r.id, r.role_name roleName from role r, user_role ur where r.id = ur.role_id and ur.user_id = #{uid}")
    List<Role> findByUid(int uid);

}
