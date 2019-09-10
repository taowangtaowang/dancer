package com.wt.overflow.controller.sys;

import com.wt.overflow.bean.Account;
import com.wt.overflow.exception.ResultUtil;
import com.wt.overflow.service.LoginService;
import com.wt.overflow.service.MenuService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 用户登录相关
 */
@Controller
@RequestMapping(value = "user")
public class LoginController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private LoginService loginService;
	@Autowired
	private MenuService menuService;

	/**
	 * 验证用户账号密码
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "checkLogin", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "登陆账号密码验证",
			notes = "通过ResultUtil.state来界定是否登陆成功", nickname = "登录-LoginController")
	public ResultUtil verifyUser(
			@ApiParam(required = true, value = "账号", name = "account") @RequestParam(value = "account") String account,
			@ApiParam(required = true, value = "密码", name = "password") @RequestParam(value = "password") String password,
			HttpServletRequest request) {
		/*String authCode = request.getParameter("code");// 验证码
		if (!Strings.isNotEmpty(authCode)) {
			parameter.put("state", "error");
		 	parameter.put("message", "验证码有误"); return parameter; 
		}
		if(!request.getSession().getAttribute("code").toString().toUpperCase().equals(authCode.toUpperCase())) {
			// 验证码通过 parameter.put("state", "error");
			parameter.put("message", "验证码错误，请重新输入"); return parameter;
		}*/
		if (!StringUtils.isEmpty(account) && !StringUtils.isEmpty(password)) {
			List<Account> accountList = loginService.queryByAccount(account);
			if (accountList.isEmpty()) {
				return ResultUtil.error("当前账号不存在");
			}
			for (Account ac : accountList) {// 账号唯一 F_Account
				if (password.equals(ac.getPassword())) {// 登录成功
					// 写入用户登录日志。
					//登录用户写进session
					request.getSession().setAttribute("loginUser", ac);
					return ResultUtil.ok("登录成功");
				}
			}
			return ResultUtil.error("账号或者密码错误，请重新输入");
		}
		return ResultUtil.error("账号或者密码输入有误");
		//发送日志
     	/*jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                MapMessage message = session.createMapMessage();
                message.setString("type", "login");
                message.setString("account", loginVO.getAccount());
                message.setString("ip", ip);
                message.setString("logintime", String.valueOf(System.currentTimeMillis()));
                return message;
            }
        });
        Map<String, String> message = new HashMap<>();
        message.put("type", "login");
        message.put("account", loginVO.getAccount());
        message.put("ip", ip);
        message.put("logintime", String.valueOf(System.currentTimeMillis()));
        redisManager.publish("logs", JSON.toJSONString(message));*/
	}


	/**
	 * 查询当前登录用户的菜单权限
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "selectShowMenus",method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "查询当前登录用户的菜单权限",
			notes = "从登录用户获取当前用户所拥有的权限", nickname = "获取菜单权限-LoginController")
	public ResultUtil selectShowMenus(HttpServletRequest request) {
		Account account =(Account) request.getSession().getAttribute("loginUser");
		List<Map<String, Object>> mapList = menuService.selectShowMenus(account);
		return ResultUtil.ok(mapList);
	}


	/**
	 * 退出登录
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="exit",method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "退出登录",
			notes = "通過清除session达到退出登录", nickname = "登录-LoginController")
	public Map<String, Object> exit(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute("loginUser", null);
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("state", "error");
		parameter.put("message", "退出登录成功");
		return parameter;
	}


}
