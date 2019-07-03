package com.wt.overflow.filter;

import com.wt.overflow.util.StringUtilClass;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


/**
 * 过滤所有的页面请求，处理html标签信息
 * @author lenovo
 */
public class RequestFilter extends OncePerRequestFilter {
	boolean beFilter = true;  
	
	public String filter(HttpServletRequest request, String input) {
		String ret = input;
               //ios客户端请求参数值可能为(null)服务端过滤掉当null处理即可
		if (input == null || input.trim().equals("(null)")) {
                       ret=null;
			return ret;
		}
		final String userAgent = request.getHeader("User-Agent");
		final String method = request.getMethod();
               //该处可以实现各种业务的自定义的过滤机制
		if (userAgent!=null&&method.equalsIgnoreCase("get")) {
				//||( method.equalsIgnoreCase("get")&&userAgent.toLowerCase().indexOf("android") != -1))) {
			try {
				ret = new String(input.getBytes("ISO8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		// 不拦截 的action
		String[] noFilters = new String[] {
       		"teacherSaveHomeworkBaseRemote",
       		"saveRemote",
       		"saveHomeworkDaily",
       		"saveHomeworkDailyFromTest",
       		"subDailyRemote",
       		"saveDailyworkRemote",
       		"clientSaveOrUpdateMinicourseHomeworkRemote",
       		"saveMinicourseDaity",
		};
		
		String uri = request.getRequestURI();  
		//暂时不转化
		if (uri.indexOf("action") != -1) {  
	            for (String s : noFilters) {  
	                if (uri.indexOf(s) != -1) {  
	                    beFilter = false;  
	                    break;  
	                }  
	            }  
		 }
		return ret;
	}

	@Override
	protected void doFilterInternal(final HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
	            	chain.doFilter(new HttpServletRequestWrapper(request) {
	        			@Override
	        			public String getParameter(String name) {
	        				String value = super.getParameter(name);
	        				return filter(this, value);
	        			}

	        			@Override
	        			public String[] getParameterValues(String name) {
	        				String[] values = super.getParameterValues(name);
	        				if (values == null) {
	        					return null;
	        				}
							if(beFilter){
	        					for (int i = 0; i < values.length; i++) {
	        						values[i] = filter(this, values[i]);
	        						values[i]  = StringUtilClass.filterStr(values[i]);
	        					}
	        				}
	        				return values;
	        			}

	        		}, response);
	}
}