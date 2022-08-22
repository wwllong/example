package com.example.hello.ipersistence.session;

import com.example.hello.ipersistence.pojo.Configuration;
import com.example.hello.ipersistence.pojo.MappedStatement;

import java.sql.SQLException;
import java.util.List;

/**
 * @author jack.wen
 * @version 1.0.0
 */
public interface Executor {

    <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object[] param) throws Exception;

    void close() throws SQLException;

}
