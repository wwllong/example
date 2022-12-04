package org.example.hello.mybatis.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * @author jack.wen
 * @since 2022/11/15 00:26
 */
@Intercepts({
        // 这里可以定义多个@Signature对多个地方拦截，都用这个拦截器
        @Signature(
                // 指拦截哪个接口
                type = Executor.class,
                // 拦截接口的某个方法名
                method = "query",
                // 拦截的方法的入参，注意顺序要正确。 拦截器根据方法名+参数 确认唯一性。
                args= { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
        )
})
public class ExamplePlugin implements Interceptor {
    /**
     * 每次执行操作的时候，都会进行这个拦截器的方法内
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("对方法进行了增强....");
        return invocation.proceed(); //执行原方法
    }

    /**
     * 包装目标对象 为目标对象创建代理对象。
     * 目的是把这个拦截器生成一个代理放到拦截器链中
     * @param target 要拦截的对象
     * @return 代理对象
     */
    @Override
    public Object plugin(Object target) {
        System.out.println("将要包装的目标对象:" + target);
        return Plugin.wrap(target, this);
    }

    /**
     * 获取配置文件的属性。
     * 插件初始化的时候调用，仅调用一次。
     * @param properties 插件配置的属性
     */
    @Override
    public void setProperties(Properties properties) {
        System.out.println("插件配置的初始化参数:" + properties );
    }
}
