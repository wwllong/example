package com.example.hello.spring.boot.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * 商品描述表
 */
@Data
@Table(name = "tb_item_desc")
public class ItemDesc implements Serializable {
    /**
     * 商品ID
     */
    @Id
    @Column(name = "item_id")
    private Long itemId;

    /**
     * 商品描述
     */
    @Column(name = "item_desc")
    private String itemDesc;

    /**
     * 创建时间
     */
    @Column(name = "created")
    private Date created;

    /**
     * 更新时间
     */
    @Column(name = "updated")
    private Date updated;

    private static final long serialVersionUID = 1L;
}