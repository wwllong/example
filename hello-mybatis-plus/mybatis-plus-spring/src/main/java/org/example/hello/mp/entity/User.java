package org.example.hello.mp.entity;

import lombok.Data;

/**
 * @author jack.wen
 * @since 2023/4/18 10:08
 */
@Data
public class User {

    private Long id;
    private String name;
    private Integer age;
    private String email;

}
