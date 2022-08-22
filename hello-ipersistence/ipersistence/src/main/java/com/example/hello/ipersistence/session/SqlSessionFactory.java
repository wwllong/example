package com.example.hello.ipersistence.session;

/**
 * @author jack.wen
 * @version 1.0.0
 * @date 2022/8/19 01:01
 */
public interface SqlSessionFactory {

    SqlSession openSession();

}
