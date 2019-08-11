package com.wt.overflow.service.impl;

import com.wt.overflow.service.RealTimeActiveMqService1;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * 发送自动接收版本
 */
@Service
public class RealTimeActiveMqServiceImpl1 implements RealTimeActiveMqService1 {

    @Resource
    private JmsTemplate jmsTemplate2;

    @Override
    public boolean send(String messageStr) {
        jmsTemplate2.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(messageStr);
            }
        });

        return true;
    }

}
