package com.baimi.init.common.utils;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zhang
 * @since 2024/7/12 上午10:08
 */
@Component
public class MqUtil {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    private static final String topic = "InitTopic";
    private static final Integer messageTimeOut = 4000;

    /*
     * 普通发送消息
     * @since  2024/7/12
     * @param  msg
     * @return void
     **/
    public void send(String msg) {
        rocketMQTemplate.convertAndSend(topic,msg);
    }
    /**
     * 同步发送消息
     * @since  2024/7/12
     * @param msg
     * @return Object
     **/

    public Object syncSend(String msg) {
        return rocketMQTemplate.syncSend(topic,msg);
    }

    /*
     * 异步发送消息
     * @since  2024/7/12
     * @param
     * @return
     **/

    public void asyncSend(String msg) {
        rocketMQTemplate.asyncSend(topic, MessageBuilder.withPayload(msg).build(), new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                // 处理消息发送成功逻辑
            }
            @Override
            public void onException(Throwable throwable) {
                // 处理消息发送异常逻辑
            }
        });
    }
    public void sendDelayMsg(String msg, int delayLevel) {
        rocketMQTemplate.syncSend(topic, MessageBuilder.withPayload(msg).build(), messageTimeOut, delayLevel);
    }
    /**
     * 发送单向消息（只负责发送消息，不等待应答，不关心发送结果，如日志）
     */
    public void sendOneWayMsg(String msg) {
        rocketMQTemplate.sendOneWay(topic, MessageBuilder.withPayload(msg).build());
    }

    /**
     * 发送带tag的消息，直接在topic后面加上":tag"
     */
    public SendResult sendTagMsg(String msg) {
        return rocketMQTemplate.syncSend(topic + ":tag2", MessageBuilder.withPayload(msg).build());
    }

}
