package com.fcant.mq_rabbitmq.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

/**
 * Producer
 * <p>
 * encoding:UTF-8
 *
 * @author Fcant 下午 21:28 2020/9/8/0008
 */
public class Producer {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException, TimeoutException {
        //连接RabbitMQ服务器
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        factory.setHost("120.79.178.68");
        factory.setPort(5672);
        //创建一个连接
        Connection conn = factory.newConnection();
        //获得信道
        Channel channel = conn.createChannel();
        //声明交换器
        channel.exchangeDeclare("ex-hello","direct");
        //发送的消息内容
        byte[] messageBodyBytes = "Hello, world!".getBytes();
        channel.basicPublish("ex-hello", "route-hello", null, messageBodyBytes);
        //关闭通道
        channel.close();
        conn.close();
    }
}
