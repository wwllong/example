package org.example.hello.mybatis.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author jack.wen
 * @version 1.0.0
 */
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;

    /**
     * 代表当前用户具备哪些订单
     */
    @Transient
    private List<Order> orderList;

    /**
     * 代表当前用户具备哪些角色
     */
    @Transient
    private transient List<Role> roleList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", orderList='" + orderList + '\'' +
                ", roleList='" + roleList + '\'' +
                '}';
    }

}
