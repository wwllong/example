package com.example.hello.ipersistence.config;

import com.example.hello.ipersistence.pojo.Configuration;
import com.example.hello.ipersistence.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * @author jack.wen
 * @version 1.0.0
 * @date 2022/8/19 02:10
 */
public class XMLMapperBuilder {

    private Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 解析SQL Mapper文件封装到mappedStatementMap
     */
    public void parse(InputStream inputStream) throws DocumentException, ClassNotFoundException {
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();
        String namespace = rootElement.attributeValue("namespace");
        List<Element> select = rootElement.selectNodes("select");

        // mappedStatement handle
        for (Element element : select) {
            String id = element.attributeValue("id");
            String parameterType = element.attributeValue("parameterType");
            String resultType = element.attributeValue("resultType");
            Class<?> parameterTypeClass = getClassType(parameterType);
            Class<?> resultTypeClass = getClassType(resultType);
            String key = namespace + "." + id;
            String textTrim = element.getTextTrim();
            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setparameterType(parameterTypeClass);
            mappedStatement.setResultType(resultTypeClass);
            mappedStatement.setSql(textTrim);
            configuration.getMappedStatementMap().put(key , mappedStatement);
        }
    }

    private Class<?> getClassType(String parameterType) throws ClassNotFoundException {
        return parameterType != null ? Class.forName(parameterType) : null;
    }

}
