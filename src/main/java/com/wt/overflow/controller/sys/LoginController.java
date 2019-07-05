package com.wt.overflow.controller.sys;

import com.wt.overflow.bean.SysUser;
import com.wt.overflow.service.LoginService;
import com.wt.overflow.util.CodeUtil;
import com.wt.overflow.util.MD5Util;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
 * 
 * @author wangt
 *
 */
@Controller
@RequestMapping(value = "login")
public class LoginController {

	@Autowired
	private LoginService loginService;

	/**
	 * 验证用户账号密码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("verifyUser")
	@ResponseBody
	public Map<String, Object> verifyUser(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		String loginname = request.getParameter("username");
		String password = request.getParameter("password");
		//String ImageCode = request.getParameter("ImageCode"); 验证标识
		String authCode = request.getParameter("code");// 验证码
		
		/*if (!Strings.isNotEmpty(authCode)) { 
			parameter.put("state", "error");
		 	parameter.put("message", "验证码有误"); return parameter; 
		}
		 
		if(!request.getSession().getAttribute("code").toString().toUpperCase().equals(authCode.toUpperCase())) {
			// 验证码通过 parameter.put("state", "error");
			parameter.put("message", "验证码错误，请重新输入"); return parameter;
		}*/
		 
		/*loginname = "admin";
		password = "123456";*/
		if (Strings.isNotEmpty(loginname) && Strings.isNotEmpty(password)) {
			List<SysUser> sysUserlist = loginService.queryByLoginName(loginname);
			if (sysUserlist.isEmpty()) {
				parameter.put("state", "error");
				parameter.put("message","当前账号不存在");
				return parameter;
			}
			if(!sysUserlist.get(0).getfEnabledmark()){
				parameter.put("state", "error");
				parameter.put("message", "当前账号已被禁用");
				return parameter;
			}
			for (SysUser sysUser : sysUserlist) {// 账号唯一 F_Account
				if (MD5Util.MD5(password).equals(sysUser.getfUserPassword().toUpperCase())) {// 登录成功
					// 写入用户登录日志。
					//登录用户写进session
					request.getSession().setAttribute("loginUser",sysUser);
					parameter.put("state", "success");
					parameter.put("message", "登录成功");
					return parameter;
				}
			}
			parameter.put("state", "error");
			parameter.put("message", "账号或者密码错误，请重新输入");
			return parameter;
		}

		parameter.put("state", "error");
		parameter.put("message", "账号或者密码输入有误");
		return parameter;
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
	 * @name wangt	
	 * @Date 2018年4月8日 下午2:35:39
	 */
	@RequestMapping("getImage")
	@ResponseBody
	public void getImage(HttpServletRequest req, HttpServletResponse resp) {
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
