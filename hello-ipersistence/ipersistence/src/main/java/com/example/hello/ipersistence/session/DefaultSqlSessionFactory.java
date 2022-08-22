package com.example.hello.ipersistence.session;

import com.example.hello.ipersistence.pojo.Configuration;

/**
 * @author jack.wen
 * @version 1.0.0
 * @date 2022/8/21 18:59
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory{

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }

}
