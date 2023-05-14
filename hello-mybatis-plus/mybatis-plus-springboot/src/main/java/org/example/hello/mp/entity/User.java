package org.example.hello.mp.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author jack.wen
 * @since 2023/4/18 10:08
 */
@Data
public class User extends Model<User>  {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    // 租户 ID
    private Long tenantId;
    @Version
    private Integer version;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableLogic
    private Integer deleted;


}
