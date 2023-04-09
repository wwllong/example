package org.example.hello.mybatis.pattern.factory;

/**
 * @author jack.wen
 * @since 2023/4/9 14:53
 */
public class Main {
    public static void main(String[] args) {
        ComputerFactory.create("lenovo").start();
        ComputerFactory.create("hp").start();
    }
}
