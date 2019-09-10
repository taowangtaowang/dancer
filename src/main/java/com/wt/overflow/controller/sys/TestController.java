package com.wt.overflow.controller.sys;

import com.wt.overflow.bean.Account;
import com.wt.overflow.exception.ResultUtil;
import com.wt.overflow.service.TestService;
import com.wt.overflow.service.impl.CustomOperationLogger;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "test")
public class TestController {


    @Autowired
    private TestService testService;


    /**
     * 测试多数据源事务问题
     * @return
     */
    @RequestMapping(value = "testTransactionManager",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value="多数据源事务测试", notes="多数据源事务测试，可以代码手动切换")
    public ResultUtil testTransactionManager(
            @ModelAttribute Account account,
            HttpServletRequest request, HttpServletResponse response) {
        int res = testService.testTransactionManager(account);
        if(res>0)
            return ResultUtil.ok("事务测试成功");
        else
            return ResultUtil.error("事务测试失败");
    }



    /**
     * 测试多数据源
     * @return
     */
    @RequestMapping(value="testSomeDataSource",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value="多数据源测试", notes="多数据源查询测试而已")
    public ResultUtil testSomeDataSource() {
        testService.testSomeDataSourceTransaction();
        return ResultUtil.ok("事务测试成功");
    }

    /**
     * 原生队列日志测试
     * @return
     */
    @RequestMapping(value="testLogToLinkedQueue",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value="原生队列日志测试", notes="使用ConcurrentLinkedQueue处理高并发场景的原生队列日志测试",nickname = "这里是实现aop的日志测试")
    public ResultUtil testLogToLinkedQueue(HttpServletRequest request) {
        //该方式可应用于操作日志的特殊处理  一般可以用aop切面时间操作日志
        CustomOperationLogger.getLogger().log(request.getRequestURI(),
                "这里是手动调用日志测试,使用ConcurrentLinkedQueue处理高并发场景的原生队列日志", "测试-TestController","无");
        return ResultUtil.ok("原生队列日志测试");
    }

}
