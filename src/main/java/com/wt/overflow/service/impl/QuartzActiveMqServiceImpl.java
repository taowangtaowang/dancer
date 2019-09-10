package com.wt.overflow.service.impl;

import com.wt.overflow.bean.Account;
import com.wt.overflow.service.QuartzActiveMqService;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

/**
 * 手动发送接收版本
 */
@Service
public class QuartzActiveMqServiceImpl implements QuartzActiveMqService {
    /*注：因为配置文件中有两个JmsTemplate,故用@Resource,可byName注入  https://blog.csdn.net/weixin_40423597/article/details/80643990
    * spring不但支持自己定义的@Autowired注解，还支持几个由JSR-250规范定义的注解，它们分别是
    * @Resource、@PostConstruct以及@PreDestroy。
　　  @Resource的作用相当于@Autowired，只不过@Autowired按byType自动注入，而@Resource默认按 byName自动注入罢了。
      @Resource有两个属性是比较重要的，分是name和type，Spring将@Resource注解的name属性解析为bean的名字，
      而type属性则解析为bean的类型。所以如果使用name属性，则使用byName的自动注入策略，而使用type属性时则使用byType自动注入策略。
      如果既不指定name也不指定type属性，这时将通过反射机制使用byName自动注入策略。
    * */
    @Resource
    private JmsTemplate jmsTemplate1;

    /**
     * 直接将消息转化并入队
     */
    @Override
    public boolean simpleSend(Account account) {
        jmsTemplate1.convertAndSend(account);
        return true;
    }
    /**
     * 发送消息到队列中
     */
    @Override
    public boolean send(String goods) {
        jmsTemplate1.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(goods);
            }
        });
        return true;
    }


    /**
     * 从队列中接收消息并返回（手动方式）
     */
    @Override
    public String receive() {
        //ActiveMQTextMessage message = (ActiveMQTextMessage)jmsTemplate1.receive();
        ObjectMessage message = (ObjectMessage) jmsTemplate1.receive();
        if (message == null) {
            return null;
        }
        try {
            Account account = (Account) message.getObject();
            return account.getAccount();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return null;
    }
}
