package com.example.hello.ipersistence.session;

import com.example.hello.ipersistence.pojo.Configuration;
import com.example.hello.ipersistence.pojo.MappedStatement;

import java.lang.reflect.*;
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

    @Override
    public <T> T getMapper(Class<?> mapperClass) throws SQLException {
        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // selectOne / selectList
                String methodName = method.getName();
                // className : namespace
                String className = method.getDeclaringClass().getName();
                // statementId: namespace.id = 接口全限名.方法名
                String key = className+"."+methodName;
                // parameter & returnType
                Type genericReturnType = method.getGenericReturnType();
                //判断是否实现泛型类型参数化
                if(genericReturnType instanceof ParameterizedType){
                    return selectList(key, args);
                }
                return selectOne(key, args);
            }});
        return (T)proxyInstance;
    }
}
