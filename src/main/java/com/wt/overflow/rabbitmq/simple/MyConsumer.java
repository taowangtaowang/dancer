package com.wt.overflow.rabbitmq.simple;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 消息消费者
 */
public class MyConsumer {
    private final static String EXCHANGE_NAME = "SIMPLE_EXCHANGE";
    private final static String QUEUE_NAME = "SIMPLE_QUEUE";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        // 连接IP
        factory.setHost("119.27.191.15");
        // 默认监听端口
        factory.setPort(5672);
        // 虚拟机
        factory.setVirtualHost("/");

        // 设置访问的用户
        factory.setUsername("admin");
        factory.setPassword("123456");
        // 建立连接
        Connection conn = factory.newConnection();
        // 创建消息通道
        Channel channel = conn.createChannel();

        // 声明交换机
        // String exchange交换机名称,
        // String type交换机类型,
        // boolean durable是否持久化，重启服务器，交换机是否存在（一般都得封装好持久化）,
        // boolean autoDelete是否自动删除 让没有一个消费者连接到该交换机时，是否自动删除,
        // Map<String, Object> arguments 交换机的其他属性  例如x-message-ttl、x-expires、x-max-length、x-maxlength-bytes、x-dead-letter-exchange、x-dead-letter-routing-key、x-max-priority
        channel.exchangeDeclare(EXCHANGE_NAME,"direct",false, false, null);

        // 声明队列
        // String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments 以上参数说明同上
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" Waiting for message....");

        // 绑定队列和交换机
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"gupao.best");

        // 创建消费者
        Consumer consumer = new DefaultConsumer(channel) {
            //properties 相关消息属性
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.println("Received message : '" + msg + "'");
                System.out.println("consumerTag : " + consumerTag );
                System.out.println("deliveryTag : " + envelope.getDeliveryTag() );
            }
        };
        // 开始获取消息
        // String queue, boolean autoAck, Consumer callback
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}

