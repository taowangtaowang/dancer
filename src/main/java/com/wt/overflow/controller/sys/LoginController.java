package com.wt.overflow.controller.sys;

import com.wt.overflow.bean.SysUser;
import com.wt.overflow.exception.ResultUtil;
import com.wt.overflow.service.LoginService;
import com.wt.overflow.util.CodeUtil;
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

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 用户登录相关
 */
@Controller
@RequestMapping(value = "login")
public class LoginController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private LoginService loginService;

	/**
	 * 验证用户账号密码
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "verifyUser",method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="登陆账号密码验证", notes="通过ResultUtil.state来界定是否登陆成功")
	public ResultUtil verifyUser(
			@ApiParam(required=true,value="账号",name="username")@RequestParam(value="username")String username,
			@ApiParam(required=true,value="密码",name="password")@RequestParam(value="password")String password,
			HttpServletRequest request) {
		String authCode = request.getParameter("code");// 验证码
		/*if (!Strings.isNotEmpty(authCode)) {
			parameter.put("state", "error");
		 	parameter.put("message", "验证码有误"); return parameter; 
		}
		 
		if(!request.getSession().getAttribute("code").toString().toUpperCase().equals(authCode.toUpperCase())) {
			// 验证码通过 parameter.put("state", "error");
			parameter.put("message", "验证码错误，请重新输入"); return parameter;
		}*/
		 
		if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
			List<SysUser> sysUserlist = loginService.queryByLoginName(username);
			if (sysUserlist.isEmpty()) {
				return ResultUtil.error("当前账号不存在");
			}
			if(sysUserlist.get(0).getEnabledmark()==0){
				return ResultUtil.error("当前账号已被禁用");
			}
			for (SysUser sysUser : sysUserlist) {// 账号唯一 F_Account
				if (password.equals(sysUser.getUserPassword())) {// 登录成功
					// 写入用户登录日志。
					//登录用户写进session
					request.getSession().setAttribute("loginUser",sysUser);
					return ResultUtil.ok("登录成功");
				}
			}
			return ResultUtil.error("账号或者密码错误，请重新输入");
		}
		return ResultUtil.error("账号或者密码输入有误");
	}

	/**
	 * 退出登录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("loginOut")
	@ResponseBody
	public Map<String, Object>  loginOut(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute("loginUser",null);
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("state", "error");
		parameter.put("message", "退出登录成功");
		return parameter;
	}


	/**
	 * 初始化数据
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("queryInitData")
	@ResponseBody
	public Map<String, Object> queryInitData(HttpServletRequest request, HttpServletResponse response) {
		return loginService.queryInitData((SysUser)request.getSession().getAttribute("loginUser"));
	}

	
	/**
	 * 将验证码写到登录页面
	 * @param req
	 * @param resp
	 */
	@RequestMapping("getImage")
	@ResponseBody
	public void getImage(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("日志测试 controller包");
		// 调用工具类生成的验证码和验证码图片
		Map<String, Object> codeMap = CodeUtil.generateCodeAndPic();

		// 将四位数字的验证码保存到Session中。
		HttpSession session = req.getSession();
		session.setAttribute("code", codeMap.get("code").toString());

		// 禁止图像缓存。
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expires", -1);
		resp.setHeader("Pragma", "no-cache");
		resp.setContentType("image/jpeg");
		//resp.addHeader("ImageCode", session.getAttribute("code").toString());// 验证码唯一
		// 将图像输出到Servlet输出流中。
		ServletOutputStream sos;
		try {
			sos = resp.getOutputStream();
			ImageIO.write((RenderedImage) codeMap.get("codePic"), "jpeg", sos);
			sos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
