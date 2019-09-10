package com.wt.overflow.log;

import com.wt.overflow.bean.Account;
import com.wt.overflow.bean.LoginLog;
import com.wt.overflow.service.LoginLogService;
import com.wt.overflow.util.UUIDUtil;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Date;

/**
 * 日志切面
 * 带参数的日志 https://www.cnblogs.com/binarysheep/p/5776740.html
 */
@Aspect
@Component
public class LogAop{

	@Autowired
	private LoginLogService loginLogService;

	@Pointcut("execution(* com.wt.overflow.controller.sys..*.*(..))")
	private void logContrllerAspect() {};

	//配置后置返回通知
	@AfterReturning(value = "logContrllerAspect()",returning="result")
	public void afterReturning(JoinPoint jp, Object result) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        Account account = (Account)session.getAttribute("loginUser");

        MethodSignature msig = (MethodSignature) jp.getSignature();
        Method method =msig.getMethod();
        if (null != method&&account!=null) {//已登陆
            LoginLog loginLog = new LoginLog();
            loginLog.setId(UUIDUtil.getUUID());
            loginLog.setOperateUser(String.valueOf(account.getId()));
           // 判断是否包含自定义的注解
           if (method.isAnnotationPresent(ApiOperation.class)) {
			   ApiOperation annotation = method.getAnnotation(ApiOperation.class);
               String nickname = annotation.nickname();
               loginLog.setNickname(nickname);
               loginLog.setCreateTime(new Date());
               loginLog.setFunctionValue(request.getRequestURI());//annotation.value()
               loginLog.setNotes(annotation.notes());
               loginLogService.add(loginLog);
           }
        }
	}
	//异常通知 用于拦截记录异常日志
	@AfterThrowing(value = "logContrllerAspect()", throwing = "e")
	public void afterthrowing(JoinPoint jp, RuntimeException e) throws ParseException {
		//异常增强处理
	}

	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
