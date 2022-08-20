package com.example.hello.ipersistence.io;

import java.io.InputStream;

/**
 * @author jack.wen
 * @version 1.0.0
 * @date 2022/8/17 01:12
 */
public class Resources {

    // 根据配置文件路径，将配置文件加载成字节流，存储在内存中
    public static InputStream getResourceAsSteam(String path){
        return Resources.class.getClassLoader().getResourceAsStream(path);
    }

}
