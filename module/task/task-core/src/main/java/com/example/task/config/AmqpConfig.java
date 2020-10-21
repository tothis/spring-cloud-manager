package com.example.task.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author 李磊
 * @since 1.0
 */
@Configuration
public class AmqpConfig {

    /**
     * 底层实际为一个信道 如每个生产者都配置不同的回调函数 只有第一个生产者生效
     * 其它生产者声明回调函数时直接报错 其它生产者不声明回调函数时时 则使用第一个生产者回调函数
     * <p>
     * 把RabbitTemplate声明为多例可解决此问题
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory) {
        return new RabbitTemplate(factory);
    }
}