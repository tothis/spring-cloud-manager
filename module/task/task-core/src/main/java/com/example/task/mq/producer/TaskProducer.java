package com.example.task.mq.producer;

import cn.hutool.core.util.IdUtil;
import com.example.task.mq.config.TaskQueueConfig;
import com.example.task.mq.entity.MessageQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * 任务队列生产者
 *
 * @author 李磊
 * @since 1.0
 */
@Slf4j
@Component
public class TaskProducer implements RabbitTemplate.ConfirmCallback
        , RabbitTemplate.ReturnCallback {

    private final RabbitTemplate rabbitTemplate;

    public TaskProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        // 消息发送到exchange回调函数
        this.rabbitTemplate.setConfirmCallback(this::confirm);
        // 消息从exchange投递到queue失败时回调函数
        this.rabbitTemplate.setReturnCallback(this::returnedMessage);
    }

    public void send(MessageQueue content) {
        long time = content.getTime();
        if (time == 0) {
            rabbitTemplate.convertAndSend(TaskQueueConfig.TASK_EXCHANGE_DEAD
                    , TaskQueueConfig.TASK_ROUTING_KEY_DEAD, content
                    , new CorrelationData(IdUtil.simpleUUID()));
        } else {
            rabbitTemplate.convertAndSend(TaskQueueConfig.TASK_EXCHANGE
                    , TaskQueueConfig.TASK_ROUTING_KEY, content
                    , message -> {
                        message.getMessageProperties().setExpiration(String.valueOf(time));
                        return message;
                    }
                    , new CorrelationData(IdUtil.simpleUUID()));
        }
    }

    /**
     * 消息发送确认 消息投递到exchange回调
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("生产者回调id " + correlationData);
        if (ack) {
            log.info("消息成功消费");
        } else {
            log.info("消息消费失败 " + cause);
        }
    }

    /**
     * 消息从交换机成功到达队列 不会被调用此方法
     * 消息从交换机未能成功到达队列 会被调用此方法
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText
            , String exchange, String routingKey) {
        log.info("returnedMessage [消息从交换机到队列失败]  message ：" + message);
    }
}