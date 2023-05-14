package org.example.hello.mp.sqlInjector;

/**
 * @author jack.wen
 * @since 2023/5/14 22:56
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface MyBaseMapper<T> extends BaseMapper<T> {

    List<T> findAll();

}
