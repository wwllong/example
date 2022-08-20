package com.example.hello.ipersistence.session;

import com.example.hello.ipersistence.config.XMLConfigBuilder;
import com.example.hello.ipersistence.pojo.Configuration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 * @author jack.wen
 * @version 1.0.0
 * @date 2022/8/19 00:59
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream inputStream) throws DocumentException, PropertyVetoException, ClassNotFoundException {
        // 1. 解析配置文件，封装Configuration
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseConfiguration(inputStream);
        // 2. 创建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(configuration);
        return sqlSessionFactory;
    }

}
