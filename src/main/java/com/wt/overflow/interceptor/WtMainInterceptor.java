package com.wt.overflow.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

@Intercepts({ @Signature(type = Executor.class, // 只能是: StatementHandler |
												// ParameterHandler |
												// ResultSetHandler | Executor
												// 类或者子类
		method = "query", // 表示：拦截Executor的query方法
		// query 有很多的重载方法，需要通过方法签名来指定具体拦截的是那个方法
		args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }) })
public class WtMainInterceptor implements Interceptor {
	// 拦截逻辑，参数是代理类
	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		BoundSql boundSql = mappedStatement.getBoundSql(invocation.getArgs()[1]);
		System.out.println(
				String.format("plugin output sql = %s , param=%s", boundSql.getSql(), boundSql.getParameterObject()));
		return invocation.proceed();
	}

	// 加载插件，一般使用Plugin.wrap(target, this);加载当前插件
	public Object plugin(Object obj) {
		return Plugin.wrap(obj, this);
	}

	// 初始化属性
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub

	}

}
