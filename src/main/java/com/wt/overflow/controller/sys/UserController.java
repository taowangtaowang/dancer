package com.wt.overflow.controller.sys;

import com.wt.overflow.bean.Account;
import com.wt.overflow.exception.ResultUtil;
import com.wt.overflow.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "user")
public class UserController {


    @Autowired
    private UserService userService;


    /**
     * 获取用户列表
     * @return
     */
    @RequestMapping(value = "getManageUser",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value="获取用户分页列表", notes="用户管理配置")
    public ResultUtil getManageUser(HttpServletRequest request, HttpServletResponse response) {
        List<Account> accountList = userService.getManageUser();
        return ResultUtil.ok(accountList);
    }



}
