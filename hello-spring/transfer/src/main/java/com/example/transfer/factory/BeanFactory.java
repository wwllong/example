package com.example.transfer.factory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工厂类，生产对象（使用反射技术）
 */
public class BeanFactory {

    /**
     * 任务一：读取解析xml，通过反射技术实例化对象并且存储待用（map集合）
     * 任务二：对外提供获取实例对象的接口（根据id获取）
     */

    private static Map<String, Object> beanMap = new HashMap<>();  // 存储对象


    static {
        // 任务一：读取解析xml，通过反射技术实例化对象并且存储待用（map集合）
        // 加载xml
        InputStream resourceAsStream = BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml");
        // 解析xml
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(resourceAsStream);
            Element rootElement = document.getRootElement();
            List<Element> beanList = rootElement.selectNodes("//bean");
            for (Element element : beanList) {
                // 处理每个bean元素，获取到该元素的id 和 class 属性
                String id = element.attributeValue("id");        // accountDao
                String clazz = element.attributeValue("class");  // com.example.transfer.dao.impl.JdbcAccountDaoImpl
                // 通过反射技术实例化对象
                Class<?> aClass = Class.forName(clazz);
                // 实例化之后的对象
                Object o = aClass.newInstance();

                // 存储到map中待用
                beanMap.put(id, o);

            }

            // 实例化完成之后维护对象的依赖关系，检查哪些对象需要传值进入，根据它的配置，我们传入相应的值
            // 有property子元素的bean就有传值需求
            List<Element> propertyList = rootElement.selectNodes("//property");
            // 解析property，获取父元素
            //<property name="AccountDao" ref="accountDao"></property>
            for (Element element : propertyList) {
                String name = element.attributeValue("name");
                String ref = element.attributeValue("ref");

                // 找到当前需要被处理依赖关系的bean
                Element parent = element.getParent();

                // 调用父元素对象的反射功能
                String parentId = parent.attributeValue("id");
                Object parentObject = beanMap.get(parentId);
                // 遍历父对象中的所有方法，找到 "set" + name
                Method[] methods = parentObject.getClass().getMethods();
                for (Method method : methods) {
                    // 该方法就是 setAccountDao(AccountDao accountDao)
                    if (method.getName().equalsIgnoreCase("set" + name)) {
                        method.invoke(parentObject, beanMap.get(ref));
                    }
                }

                // 把处理之后的parentObject重新放到map中
                beanMap.put(parentId, parentObject);
            }


        } catch (DocumentException | ClassNotFoundException | IllegalAccessException | InstantiationException |
                 InvocationTargetException e) {
            e.printStackTrace();
        }

    }


    // 任务二：对外提供获取实例对象的接口（根据id获取）
    public static Object getBean(String id) {
        return beanMap.get(id);
    }

}
