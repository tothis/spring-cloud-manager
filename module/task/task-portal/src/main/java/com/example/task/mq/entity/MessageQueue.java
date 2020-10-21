package com.example.task.mq.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * mq队列数据 amqp发送实体需实现序列号接口
 *
 * @author 李磊
 * @since 1.0
 */
@Data
public class MessageQueue implements Serializable {
    /**
     * 业务id
     */
    private long id;
    /**
     * 延时时间 单位毫秒
     */
    private long time;
    /**
     * 消息类型
     */
    private Type type;

    public enum Type {
        /**
         * 自动取消任务
         */
        AUTO_CANCEL
    }
}