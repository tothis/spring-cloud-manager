package com.example.task.mq.consumer;

import com.example.task.mq.config.TaskQueueConfig;
import com.example.task.mq.entity.MessageQueue;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 处理任务队列消息
 *
 * @author 李磊
 * @since 1.0
 */
@Slf4j
@Component
@RabbitListener(queues = TaskQueueConfig.TASK_QUEUE_DEAD)
public class TaskReceiver {

    @RabbitHandler
    public void process(MessageQueue content, Channel channel, Message message)
            throws IOException {
        System.out.println("消息为 -> " + content.toString());
        // 防止重复消费 可根据传过来的唯一id进行区分
        try {
            MessageQueue.Type type = content.getType();
            switch (type) {
                case AUTO_CANCEL:
                    // 自动取消任务
                    System.out.println("取消任务");
                    break;
                default:
                    break;
            }

            // 发送成功确认ACK 告诉服务器当前消息已经消费 发送成功当前消费消息会被清除
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
            // 发送失败确认ACK 消息删除或重放至队列 参数3 为true 会把消费失败消息重新添加到队列尾端 为false 则删除此信息
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }
}