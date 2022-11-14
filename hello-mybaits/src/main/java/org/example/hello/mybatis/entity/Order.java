package org.example.hello.mybatis.entity;

/**
 * @author jack.wen
 * @since 2022/10/26 23:58
 */
public class Order {

    private int id;
    private String orderNo;
    private Double amount;
    /**
     * 代表当前订单从属于哪一个客户
     */
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", amount=" + amount +
                ", user=" + user +
                '}';
    }
}
