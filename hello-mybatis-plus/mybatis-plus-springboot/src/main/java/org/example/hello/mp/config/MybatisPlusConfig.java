package org.example.hello.mp.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.*;
import org.example.hello.mp.sqlInjector.MySqlInjector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jack.wen
 * @since 2023/5/10 00:02
 */
@Configuration
@MapperScan("org.example.hello.mp")
public class MybatisPlusConfig {


    /**
     * 新的分页插件
     * 一缓和二缓遵循MyBatis的规则, 需要设置 MybatisConfiguration#useDeprecatedExecutor = false, 避免缓存出现问题(该属性会在旧插件移除后一同移除)
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 租户插件
//        interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new MyTenantLineHandler()));
        // 乐观锁插件
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        // 分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        // 性能规范插件
//        interceptor.addInnerInterceptor(new IllegalSQLInnerInterceptor());
        // 防止全表更新与删除插件
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        return interceptor;
    }

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setUseGeneratedKeys(false);
    }

    /**
     * ⾃定义SQL注⼊器
     */
    @Bean
    public MySqlInjector mySqlInjector(){
        return new MySqlInjector();
    }

}
