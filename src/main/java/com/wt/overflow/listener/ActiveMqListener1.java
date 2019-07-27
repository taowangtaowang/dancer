package com.wt.overflow.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class ActiveMqListener1 implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.err.println("接收到一个纯文本消息：activeMqTest21自动版本测试——MessageListener");
        try {
            System.out.println("消息内容是：" + (String) ((ObjectMessage) message).getObject());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
