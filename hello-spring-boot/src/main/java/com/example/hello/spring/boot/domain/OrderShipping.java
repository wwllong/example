package com.example.hello.spring.boot.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "tb_order_shipping")
public class OrderShipping implements Serializable {
    /**
     * 订单ID
     */
    @Id
    @Column(name = "order_id")
    private String orderId;

    /**
     * 收货人全名
     */
    @Column(name = "receiver_name")
    private String receiverName;

    /**
     * 固定电话
     */
    @Column(name = "receiver_phone")
    private String receiverPhone;

    /**
     * 移动电话
     */
    @Column(name = "receiver_mobile")
    private String receiverMobile;

    /**
     * 省份
     */
    @Column(name = "receiver_state")
    private String receiverState;

    /**
     * 城市
     */
    @Column(name = "receiver_city")
    private String receiverCity;

    /**
     * 区/县
     */
    @Column(name = "receiver_district")
    private String receiverDistrict;

    /**
     * 收货地址，如：xx路xx号
     */
    @Column(name = "receiver_address")
    private String receiverAddress;

    /**
     * 邮政编码,如：310001
     */
    @Column(name = "receiver_zip")
    private String receiverZip;

    @Column(name = "created")
    private Date created;

    @Column(name = "updated")
    private Date updated;

    private static final long serialVersionUID = 1L;
}