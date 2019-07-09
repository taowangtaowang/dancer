package com.wt.overflow.service.impl;

import com.wt.overflow.service.RealTimeActiveMqService1;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;

/**
 * 发送自动接收版本
 */
@Service
public class RealTimeActiveMqServiceImpl1 implements RealTimeActiveMqService1,MessageListener {
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

    @Override
    public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {
            try {
                boolean res = false;
                String receive = "";
                try {
                    receive = (String) ((ObjectMessage) message).getObject();//获取队列 里面的消息进行操作
                    System.out.println("receive:"+receive);res=true;
                }catch (Exception e){//消息处理时候的异常
                    res = false;
                }
                if (!res) {
                    send(receive);
                }
            } catch (Exception e) { //消息取出来置换后处理错误？ 发送消息错误？ 后续的处理方式？
                e.printStackTrace();
            }
        }
    }



}
