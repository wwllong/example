package org.example.hello.mybatis.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author jack.wen
 * @since 2023/4/9 15:43
 */
public class JDKDynamicProxy implements InvocationHandler {
    // 被代理对象
    Person target;

    public JDKDynamicProxy(Person target) {
        this.target = target;
    }

    public Person getTarget() {
        return (Person) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 被代理⽅法前执⾏
        System.out.println("JDKDynamicProxy do something before!");
        // 执⾏被代理的⽅法
        Person result = (Person) method.invoke(target, args);
        // 被代理⽅法后执⾏
        System.out.println("JDKDynamicProxy do something after!");
        return result;
    }
}
