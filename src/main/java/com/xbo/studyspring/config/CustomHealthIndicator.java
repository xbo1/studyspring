package com.xbo.studyspring.config;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

//这里也可以使用 类似@ConditionalOnMissingBean写法自动加载的
//这里的name 就是默认健康节点的名称了
@Component("xbo")
public class CustomHealthIndicator extends AbstractHealthIndicator {
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        //设置健康信息
        builder.withDetail("code", "0123")
                .withDetail("version", "v0.1")
                //有其他信息可继续添加的
                .up().build();
    }
}