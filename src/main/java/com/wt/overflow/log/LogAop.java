package com.wt.overflow.log;

import com.wt.overflow.bean.OperationLog;
import com.wt.overflow.bean.SysUser;
import com.wt.overflow.service.OperationLogService;
import com.wt.overflow.util.UUIDUtil;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Date;

/**
 * 日志切面
 */
@Aspect
public class LogAop{

	@Autowired
	private OperationLogService operationLogService;

	@Pointcut("execution(* com.wt.overflow.controller.sys..*.*(..))")
	private void logContrllerAspect() {};
	
	@AfterReturning(value = "logContrllerAspect()",returning="result")
	public void afterReturning(JoinPoint jp, Object result) throws Throwable {
		OperationLog opLog = new OperationLog();
		opLog.setFlowId(UUIDUtil.getUUID());
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
		HttpServletRequest request = servletRequestAttributes.getRequest();
		SysUser sysUser = (SysUser)request.getAttribute("loginUser");
		opLog.setOperateUser(sysUser.getId());

        MethodSignature msig = (MethodSignature) jp.getSignature();
        Method method =msig.getMethod();
        if (null != method) {
           // 判断是否包含自定义的注解
           if (method.isAnnotationPresent(ApiOperation.class)) {
			    ApiOperation annotation = method.getAnnotation(ApiOperation.class);
                String nickname = annotation.nickname();
			    opLog.setNickname(nickname);
			    opLog.setCreateTime(new Date());
                opLog.setFunctionValue(annotation.value());
			    opLog.setNotes(annotation.notes());
                operationLogService.add(opLog);
              }
        	}
	}
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
