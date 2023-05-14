package org.example.hello.mp.config;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;

/**
 * @author jack.wen
 * @since 2023/5/14 17:58
 */
public class MyTenantLineHandler implements TenantLineHandler {
    @Override
    public Expression getTenantId() {
        // 固定租户ID=1
        return new LongValue(1);
    }

}
