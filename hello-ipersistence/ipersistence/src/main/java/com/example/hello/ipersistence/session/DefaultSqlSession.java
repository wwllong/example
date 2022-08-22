package com.example.hello.ipersistence.session;

import com.example.hello.ipersistence.pojo.Configuration;
import com.example.hello.ipersistence.pojo.MappedStatement;

import java.sql.SQLException;
import java.util.List;

/**
 * @author jack.wen
 * @version 1.0.0
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    private Executor simpleExecutor = new SimpleExecutor();

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object... param) throws Exception {
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
        List<E> query = simpleExecutor.query(configuration, mappedStatement, param);
        return query;
    }

    @Override
    public <T> T selectOne(String statementId, Object... params) throws Exception {
        List<Object> objects = selectList(statementId, params);
        if (objects.size() == 1) {
            return (T) objects.get(0);
        } else {
            throw new RuntimeException("select one, but find more");
        }
    }

    @Override
    public void close() throws SQLException {
        simpleExecutor.close();
    }
}
