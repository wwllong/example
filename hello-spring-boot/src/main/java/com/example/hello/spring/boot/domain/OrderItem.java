package com.example.hello.spring.boot.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {
    @Id
    @Column(name = "id")
    private String id;

    /**
     * 商品id
     */
    @Column(name = "item_id")
    private String itemId;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private String orderId;

    /**
     * 商品购买数量
     */
    @Column(name = "num")
    private Integer num;

    /**
     * 商品标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 商品单价
     */
    @Column(name = "price")
    private Long price;

    /**
     * 商品总金额
     */
    @Column(name = "total_fee")
    private Long totalFee;

    /**
     * 商品图片地址
     */
    @Column(name = "pic_path")
    private String picPath;

    private static final long serialVersionUID = 1L;
}