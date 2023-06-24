package com.example.transfer.factory;

import com.example.transfer.utils.ConnectionUtils;

public class CreateBeanFactory {
    public static ConnectionUtils getInstanceStatic() {
        return new ConnectionUtils();
    }

    public ConnectionUtils getInstance() {
        return new ConnectionUtils();
    }
}
