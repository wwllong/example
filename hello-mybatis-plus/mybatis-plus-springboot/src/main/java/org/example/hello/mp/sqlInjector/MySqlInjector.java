package org.example.hello.mp.sqlInjector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;

import java.util.List;

/**
 * @author jack.wen
 * @since 2023/5/14 23:01
 */
public class MySqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        // 扩充⾃定义的⽅法
        methodList.add(new FindAll());
        return methodList;
    }

}
