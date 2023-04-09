package org.example.hello.mybatis.pattern.proxy;

/**
 * @author jack.wen
 * @since 2023/4/9 15:46
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("不使用代理类，调用doSomething方法");
        Person person = new Jack();
        person.doSomething();
        System.out.println("使用代理类，调用doSomething方法");
        Person proxyPerson = new JDKDynamicProxy(new Jack()).getTarget();
        proxyPerson.doSomething();
    }

}
