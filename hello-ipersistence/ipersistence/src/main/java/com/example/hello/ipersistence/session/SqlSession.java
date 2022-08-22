package com.example.hello.ipersistence.session;

import java.sql.SQLException;
import java.util.List;

/**
 * @author jack.wen
 * @version 1.0.0
 */
public interface SqlSession {

    <E> List<E> selectList(String statementId, Object... param) throws Exception;

    <T> T selectOne(String statementId,Object... params) throws Exception;

    void close() throws SQLException;

}
