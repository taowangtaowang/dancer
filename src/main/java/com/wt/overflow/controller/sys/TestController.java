package com.wt.overflow.controller.sys;

import com.wt.overflow.bean.SysUser;
import com.wt.overflow.exception.ResultUtil;
import com.wt.overflow.service.TestService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Controller
@RequestMapping(value = "test")
public class TestController {


    @Autowired
    private TestService testService;


    /**
     * 测试事务
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("testTransactionManager")
    @ResponseBody
    @ApiOperation(value="此api只做测试使用-testcontroller", notes="此api只做测试使用")
    public ResultUtil testTransactionManager(
            @ModelAttribute SysUser sysUser,
                    HttpServletRequest request, HttpServletResponse response) {
        int res = testService.testTransactionManager(sysUser);
        if(res>0)
            return ResultUtil.ok("事务测试成功");
        else
            return ResultUtil.error("事务测试失败");
    }


}
