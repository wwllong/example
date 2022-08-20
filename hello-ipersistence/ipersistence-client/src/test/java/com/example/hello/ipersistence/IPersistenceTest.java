package com.example.hello.ipersistence;

import com.example.hello.ipersistence.io.Resources;

import java.io.InputStream;

/**
 * @author jack.wen
 * @version 1.0.0
 * @date 2022/8/17 01:17
 */
public class IPersistenceTest {

    public void test(){
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");


    }

}
