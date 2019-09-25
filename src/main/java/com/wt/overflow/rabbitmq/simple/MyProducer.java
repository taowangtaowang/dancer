package com.wt.overflow.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 消息生产者
 */
public class MyProducer {
    private final static String EXCHANGE_NAME = "SIMPLE_EXCHANGE";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        // 连接IP
        factory.setHost("119.27.191.15");
        // 连接端口
        factory.setPort(5672);
        // 虚拟机
        factory.setVirtualHost("/");
        // 用户
        factory.setUsername("admin");
        factory.setPassword("123456");

        // 建立连接
        Connection conn = factory.newConnection();
        // 创建消息通道
        Channel channel = conn.createChannel();

        // 发送消息
        String msg = "Hello world, Rabbit MQ";
        // String exchange 交换机名称, String routingKey 路由关键字, BasicProperties props消息属性, byte[] body 消息实体
        channel.basicPublish(EXCHANGE_NAME, "gupao.best", null, msg.getBytes());
        channel.close();
        conn.close();
    }
}

