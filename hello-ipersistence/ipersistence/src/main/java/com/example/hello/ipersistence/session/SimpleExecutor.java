package com.example.hello.ipersistence.session;

import com.example.hello.ipersistence.pojo.Configuration;
import com.example.hello.ipersistence.pojo.MappedStatement;
import com.example.hello.ipersistence.utils.GenericTokenParser;
import com.example.hello.ipersistence.utils.ParameterMapping;
import com.example.hello.ipersistence.utils.ParameterMappingTokenHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jack.wen
 * @version 1.0.0
 */
public class SimpleExecutor implements Executor{

    private Connection connection = null;

    @Override
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object[] param) throws Exception {
        // 获取连接
        connection = configuration.getDataSource().getConnection();
        // select * from user where id = #{id} and username = #{username}
        String sql = mappedStatement.getSql();
        // 对sql进⾏处理, 转换为JDBC能处理的一些信息
        BoundSql boundsql = getBoundSql(sql);
        // select * from where id = ? and username = ?
        String finalSql = boundsql.getSqlText();
        // 获取传⼊参数类型
        Class<?> parameterType = mappedStatement.getparameterType();
        // 获取预编译preparedStatement对象
        PreparedStatement preparedStatement = connection.prepareStatement(finalSql);
        List<ParameterMapping> parameterMappingList = boundsql.getParameterMappingList();
        for (int i = 0; i < parameterMappingList.size(); i++) {
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String name = parameterMapping.getContent();
            // 反射
            Field declaredField = parameterType.getDeclaredField(name);
            declaredField.setAccessible(true);
            // 参数的值
            Object o = declaredField.get(param[0]);
            // 给占位符赋值
            preparedStatement.setObject(i + 1, o);
        }
        // 执行sql
        ResultSet resultSet = preparedStatement.executeQuery();

        // 封装返回结果集
        Class<?> resultType = mappedStatement.getResultType();
        List<E> results = new ArrayList<>();
        while (resultSet.next()) {
            Object o = resultType.newInstance();
            // 元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                // 属性名
                String columnName = metaData.getColumnName(i);
                // 属性值
                Object value = resultSet.getObject(columnName);
                // 创建属性描述器，为属性⽣成读写⽅法，即使用反射或者内省，根据数据库表和实体的对应关系，完成封装
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultType);
                //获取写⽅法
                Method writeMethod = propertyDescriptor.getWriteMethod();
                //向类中写⼊值
                writeMethod.invoke(o, value);
            }
            results.add((E)o);
        }

        return results;
    }

    /**
     * 完成对#{}的解析工作：1.将#{}使用？代替 ，2.解析出#{}里面的值进行存储
     */
    private BoundSql getBoundSql(String sql) {
        // 标记处理类：主要是配合通⽤标记解析器GenericTokenParser类完成对配置⽂件等的解析⼯作，其中TokenHandler主要完成处理
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        // GenericTokenParser :通⽤的标记解析器，完成了代码⽚段中的占位符的解析，然后再根据给定的标记处理器(TokenHandler)来进⾏表达式的处理
        // 三个参数：分别为openToken (开始标记)、closeToken (结束标记)、handler (标记处理器)
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        String parse = genericTokenParser.parse(sql);
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();
        return new BoundSql(parse, parameterMappings);
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }

}
