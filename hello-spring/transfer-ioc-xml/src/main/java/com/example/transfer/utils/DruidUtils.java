package com.example.transfer.utils;

import com.alibaba.druid.pool.DruidDataSource;


public class DruidUtils {

    private DruidUtils(){
    }

    private static DruidDataSource druidDataSource = new DruidDataSource();


    static {
//        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/transfer_bank");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("123456");

    }

    public static DruidDataSource getInstance() {
        return druidDataSource;
    }

}
