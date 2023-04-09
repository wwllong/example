package org.example.hello.mybatis.pattern.proxy;

/**
 * @author jack.wen
 * @since 2023/4/9 15:42
 */
public class Jack implements Person {
    @Override
    public void doSomething() {
        System.out.println("Jack doing something!");
    }
}
