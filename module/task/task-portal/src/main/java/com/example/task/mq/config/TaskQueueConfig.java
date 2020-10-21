package com.example.task.mq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 任务自动审核队列配置
 *
 * @author 李磊
 * @since 1.0
 */
@Configuration
public class TaskQueueConfig {

    public static final String TASK_QUEUE = "task-queue";
    public static final String TASK_EXCHANGE = "task-exchange";
    public static final String TASK_ROUTING_KEY = "task-routing-key";

    public static final String TASK_QUEUE_DEAD = "task-queue-dead";
    public static final String TASK_EXCHANGE_DEAD = "task-exchange-dead";
    public static final String TASK_ROUTING_KEY_DEAD = "task-routing-key-dead";

    /**
     * 死信队列
     */
    @Bean
    public Queue taskQueueDead() {
        return new Queue(TASK_QUEUE_DEAD, true);
    }

    @Bean
    public DirectExchange taskExchangeDead() {
        return ExchangeBuilder
                .directExchange(TASK_EXCHANGE_DEAD)
                .durable(true)
                .build();
    }

    @Bean
    public Binding taskBindingExchangeDead(Queue taskQueueDead
            , DirectExchange taskExchangeDead) {
        return BindingBuilder
                .bind(taskQueueDead)
                .to(taskExchangeDead)
                .with(TASK_ROUTING_KEY_DEAD);
    }

    @Bean
    public Queue taskQueue() {
        // 将普通队列绑定到死信队列交换机
        return QueueBuilder
                .durable(TASK_QUEUE)
                // 配置到期后转发的交换
                .withArgument("x-dead-letter-exchange", TASK_EXCHANGE_DEAD)
                // 配置到期后转发的路由键
                .withArgument("x-dead-letter-routing-key", TASK_ROUTING_KEY_DEAD)
                // 延时时间此处可以写死 也可由生产者发布时指定 单位毫秒 5秒->5000 24小时->86400000
                // .withArgument("x-message-ttl", 5000)
                .build();
    }

    @Bean
    public DirectExchange taskQueueExchange() {
        return new DirectExchange(TASK_EXCHANGE);
    }

    @Bean
    public Binding taskBindingExchange(Queue taskQueue
            , DirectExchange taskQueueExchange) {
        return BindingBuilder.bind(taskQueue)
                .to(taskQueueExchange)
                .with(TASK_ROUTING_KEY);
    }
}