package com.wt.overflow.filter;


import com.wt.overflow.util.PropertyGetUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * spring过滤器，预处理日期格式
 */
public class VerifyIsLoginFilter {
	//public class VerifyIsLoginFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// 将请求和响应强制转换成Http形式
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String servletPath = request.getServletPath();
		List<String> strlist  = new PropertyGetUtil().getProperties("filter.url", "/filter.properties");

		if(request.getSession().getAttribute("loginUser")==null||request.getSession().getAttribute("loginUser").equals("")){
			//未登录
			boolean isforward = false;
			for (String str : strlist) {//过滤url
				if(servletPath.contains(str)){
					chain.doFilter(request, response);
					isforward = true;
					return;
				}
			}
			if(!isforward){//非过滤url
				request.getRequestDispatcher("login.html").forward(request, response);
				return;
			}
		}else{//已经登录
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
	}
}
