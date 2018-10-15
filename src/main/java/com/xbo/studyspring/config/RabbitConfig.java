package com.xbo.studyspring.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    /**
     * 定义一个名为：oKong 的队列
     * @return
     */
    @Bean
    public Queue xboQueue() {
        return new Queue("xbo");
    }
}