package com.fcant.mq_rabbitmq.mq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * SimpleConsumer
 * <p>
 * encoding:UTF-8
 *
 * @author Fcant 下午 21:40 2020/9/8/0008
 */
public class SimpleConsumer extends DefaultConsumer {

    public SimpleConsumer(Channel channel) {
        super(channel);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        //接受从队列中发送的消息
        System.out.println(consumerTag);
        System.out.println("-----收到消息了---------------");
        System.out.println("消息属性为："+properties);
        System.out.println("消息内容为："+new String(body));
    }
}
