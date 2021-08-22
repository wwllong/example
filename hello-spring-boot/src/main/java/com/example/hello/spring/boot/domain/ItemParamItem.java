package com.example.hello.spring.boot.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * 商品规格和商品的关系表
 */
@Data
@Table(name = "tb_item_param_item")
public class ItemParamItem implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 商品ID
     */
    @Column(name = "item_id")
    private Long itemId;

    /**
     * 参数数据，格式为json格式
     */
    @Column(name = "param_data")
    private String paramData;

    @Column(name = "created")
    private Date created;

    @Column(name = "updated")
    private Date updated;

    private static final long serialVersionUID = 1L;
}