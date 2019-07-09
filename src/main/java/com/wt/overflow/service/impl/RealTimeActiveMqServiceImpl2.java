package com.wt.overflow.service.impl;

import com.wt.overflow.service.RealTimeActiveMqService2;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;

/**
 * 发送自动接收版本
 */
@Service
public class RealTimeActiveMqServiceImpl2 implements RealTimeActiveMqService2,SessionAwareMessageListener {
    @Resource
    private JmsTemplate jmsTemplate2;
    private Destination destination;

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


    public void onMessage(Message message, Session session) throws JMSException {
        System.out.println("收到一条消息");
       String receive = (String) ((ObjectMessage) message).getObject();
        System.out.println("消息内容是：" + receive);
        //message.setJMSReplyTo(Destination);

        long conf = 12345678;
        MessageProducer sender = session.createProducer(message.getJMSReplyTo());
        StreamMessage smsg = session.createStreamMessage();
        smsg.setJMSCorrelationID(message.getJMSMessageID());
        smsg.writeLong(conf);
        sender.send(smsg);

        /*MessageProducer producer = session.createProducer(destination);
        Message textMessage = session.createTextMessage("ConsumerSessionAwareMessageListener。。。");
        producer.send(textMessage);*/
    }
    public Destination getDestination() {
        return destination;
    }
    public void setDestination(Destination destination) {
        this.destination = destination;
    }

}
