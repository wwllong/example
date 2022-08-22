package com.example.hello.ipersistence.session;

import com.example.hello.ipersistence.utils.ParameterMapping;

import java.util.List;

/**
 * @author jack.wen
 * @version 1.0.0
 */
public class BoundSql {

    // 解析过后的sql语句
    private String sqlText;

    // 解析出来的参数
    private List<ParameterMapping> parameterMappingList;

    public BoundSql(String sqlText, List<ParameterMapping> parameterMappingList) {
        this.sqlText = sqlText;
        this.parameterMappingList = parameterMappingList;
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public List<ParameterMapping> getParameterMappingList() {
        return parameterMappingList;
    }

    public void setParameterMappingList(List<ParameterMapping> parameterMappingList) {
        this.parameterMappingList = parameterMappingList;
    }

}
