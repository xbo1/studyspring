package com.xbo.studyspring.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 *  mybatisPlus 配置类，使其加载配置文件
 *
 */
@Configuration
//@ImportResource(locations = {"classpath:/mybatis/spring-mybatis.xml"})
//@EnableTransactionManagement
public class MybatisPlusConfig {
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}