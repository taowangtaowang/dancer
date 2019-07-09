package com.wt.overflow.controller.sys;

import com.wt.overflow.bean.SysUser;
import com.wt.overflow.exception.ResultUtil;
import com.wt.overflow.service.QuartzActiveMqService;
import com.wt.overflow.service.RealTimeActiveMqService1;
import com.wt.overflow.service.RealTimeActiveMqService2;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 该类用于mq测试
 * https://zw1127.iteye.com/blog/1844509
 * https://www.jianshu.com/p/dd2a99b20afe
 */
@Controller
@RequestMapping(value = "activeMq/")
public class ActiveMqController {

    @Autowired
    private QuartzActiveMqService quartzActiveMqService;
    @Autowired
    private RealTimeActiveMqService1 realTimeActiveMqService1;
    @Autowired
    private RealTimeActiveMqService2 realTimeActiveMqService2;

    /**
     * activeMq手动发送接收版本测试
     * @return
     */
    @RequestMapping(value = "activeMqTest1",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value="activeMqTest1手动版本测试", notes="直接手动调用发送手动调用接收")
    public ResultUtil activeMqTest1(
            @ApiParam(required=true,value="账号",name="messageStr")@RequestParam(value="messageStr")String messageStr){
        SysUser sysUser = new SysUser();sysUser.setRealname("发送的消息是："+messageStr);
        quartzActiveMqService.simpleSend(sysUser);//直接将消息转化并入队
        //发送mqService.send(messageStr);  两者的区别？？
        return ResultUtil.ok(quartzActiveMqService.receive());
        //return ResultUtil.error("当前账号已被禁用");
    }


    /**
     * activeMq 自动监听模式发送接收版本测试 3种监听模式
     * 1-----javax.jms.MessageListener
     *          MessageListener是最原始的消息监听器，它是JMS规范中定义的一个接口。其中定义了一个用于处理接收到的消息的
     *          onMessage方法，该方法只接收一个Message参数
     * 2-----SessionAwareMessageListener
     *      SessionAwareMessageListener是Spring为我们提供的，它不是标准的JMS MessageListener。假如我们在接收到消息后发送
     *      一个回复的消息，这个时候我们就需要在代码里面去重新获取一个Connection或Session。SessionAwareMessageListener的
     *      设计就是方便了我们回复一个消息，它同样提供了onMessage方法，但这个方法可以同时接收两个参数，一个是表示当前接
     *      收到的消息Message，另一个就是可以用来发送消息的Session对象
     *
     */

    /**
     * javax.jms.MessageListener 版本
     *          MessageListener是最原始的消息监听器，它是JMS规范中定义的一个接口。其中定义了一个用于处理接收到的消息的
     *          onMessage方法，该方法只接收一个Message参数
     * @param messageStr
     * @return
     */
    @RequestMapping(value = "activeMqTest21",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value="activeMqTest21自动版本测试——MessageListener", notes="通过监听自动接收版本-MessageListener")
    public ResultUtil activeMqTest21(
            @ApiParam(required=true,value="账号",name="messageStr")@RequestParam(value="messageStr")String messageStr){
        realTimeActiveMqService1.send(messageStr);//发送消息到队列中
        return ResultUtil.ok("");
        //弊端  好像拿不到返回值的消息
    }
    /**
     * SessionAwareMessageListener 版本
     *      SessionAwareMessageListener是Spring为我们提供的，它不是标准的JMS MessageListener。假如我们在接收到消息后发送
     *      一个回复的消息，这个时候我们就需要在代码里面去重新获取一个Connection或Session。SessionAwareMessageListener的
     *      设计就是方便了我们回复一个消息，它同样提供了onMessage方法，但这个方法可以同时接收两个参数，一个是表示当前接
     *      收到的消息Message，另一个就是可以用来发送消息的Session对象
     *
     *      该MessageListener中通过set方法注入其属性destination的值为queueDestination。这样当我们的
     *      SessionAwareMessageListener接收到消息之后就会往queueDestination发送一个消息。destination也可以通过
     *      message.getJMSReplyTo()来获得。在发送消息时可通过message.setJMSReplyTo(replyQueue)来设置这个回复队列地址。
     * @param messageStr
     * @return
     */
    @RequestMapping(value = "activeMqTest22",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value="activeMqTest22自动版本测试——SessionAwareMessageListener", notes="通过监听自动接收版本-SessionAwareMessageListener")
    public ResultUtil activeMqTest22(
            @ApiParam(required=true,value="账号",name="messageStr")@RequestParam(value="messageStr")String messageStr){
        realTimeActiveMqService2.send(messageStr);//发送消息到队列中
        //message.getJMSReplyTo()
        return ResultUtil.ok("");

    }





}
