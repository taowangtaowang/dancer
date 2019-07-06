package com.wt.overflow.interceptor;

import com.wt.overflow.util.dialect.Dialect;
import com.wt.overflow.util.dialect.MsSqlDialect;
import com.wt.overflow.util.dialect.MySqlDialect;
import com.wt.overflow.util.dialect.OracleDialect;
import com.wt.overflow.util.page.Page;
import com.wt.overflow.util.page.ReflectHelper;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import javax.xml.bind.PropertyException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


/**
 * 查询分页拦截器，用户拦截SQL，并加上分页的参数和高级查询条件 
 * 
 * /**
 * 原生分页拦截器，用于拦截需要进行分页查询的操作，然后对其进行分页处理 利用拦截器实现Mybatis分页的原理:
 * 要利用JDBC对数据库进行操作就必须要有一个对应的Statement对象，
 * Mybatis在执行Sql语句前就会产生一个包含Sql语句的Statement对象， 而且对应的Sql语句 是在Statement之前产生的，
 * 所以我们就可以在它生成Statement之前对用来生成Statement的Sql语句下手。
 * 在Mybatis中Statement语句是通过RoutingStatementHandler对象的prepare方法生成的。
 * 所以利用拦截器实现Mybatis分页的一个思路就是拦截StatementHandler接口的prepare方法，
 * 然后在拦截器方法中把Sql语句改成对应的分页查询Sql语句，之后再调用
 * StatementHandler对象的prepare方法，即调用invocation.proceed()。
 * 对于分页而言，在拦截器里面我们还需要做的一个操作就是统计满足当前 条件的记录一共有多少，这是通过获取到了原始的Sql语句后，
 * 把它改为对应的统计语句再利用Mybatis封装好的参数和设 置参数的功能把Sql语句中的参数进行替换，
 * 之后再执行查询记录数的Sql语句进行总记录数的统计。
 * 
 */
@SuppressWarnings("restriction")
@Intercepts( { @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PaginationInterceptor implements Interceptor {

	private static String dialect = "";
	private static String pageSqlId = "";

	@SuppressWarnings("unchecked")
	public Object intercept(Invocation ivk) throws Throwable {

		/*
		 * 对于StatementHandler其实只有两个实现类:一个是RoutingStatementHandler，
		 * 另一个是抽象类BaseStatementHandler
		 * BaseStatementHandler有三个子类，分别是:SimpleStatementHandler
		 * ，PreparedStatementHandler和CallableStatementHandler，
		 * SimpleStatementHandler是用于处理Statement的，
		 * PreparedStatementHandler是处理PreparedStatement的，
		 * 而CallableStatementHandler是 处理CallableStatement的。
		 * Mybatis在进行Sql语句处理的时候都是建立的RoutingStatementHandler，
		 * 而在RoutingStatementHandler里面拥有一个 StatementHandler类型的delegate属性，
		 * RoutingStatementHandler会依据Statement的不同建立对应的BaseStatementHandler，
		 * 即SimpleStatementHandler
		 * 、PreparedStatementHandler或CallableStatementHandler，
		 * 在RoutingStatementHandler里面所有StatementHandler接口方法的实现都是调用的delegate对应的方法
		 * 。
		 * 
		 * 我们在PageInterceptor类上已经用@
		 * Signature标记了该Interceptor只拦截StatementHandler接口的prepare方法，	
		 * 		StatementHandler (prepare准备, parameterize参数化, batch批处理, update更新, query查询)
		 *  	ResultSetHandler (handleResultSets, handleOutputParameters)
		 *  	ParameterHandler (getParameterObject, setParameters)
		 *  	Executor (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)
		 *  			分页也可以用Executor——query
		 *  
		 * 又因为Mybatis只有在建立RoutingStatementHandler的时候是通过Interceptor的plugin方法进行包裹的
		 * ， 所以我们这里拦截到的目标对象肯定是RoutingStatementHandler对象。
		 */
		
		
		if (ivk.getTarget() instanceof RoutingStatementHandler) {
			RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk
					.getTarget();
			BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper
					.getValueByFieldName(statementHandler, "delegate");
			MappedStatement mappedStatement = (MappedStatement) ReflectHelper
					.getValueByFieldName(delegate, "mappedStatement");

			if (mappedStatement.getId().matches(pageSqlId)) {
				// BoundSql封装了sql语句 
				BoundSql boundSql = delegate.getBoundSql();
				// 获得查询对象
				Object parameterObject = boundSql.getParameterObject();
				if (parameterObject == null) {
					throw new NullPointerException("parameterObject");
				} else {
					/*Connection connection = (Connection) ivk.getArgs()[0];
					String sql = boundSql.getSql();
					String countSql = "SELECT COUNT(0) FROM (" + sql + ") AS TOTAL ";
					PreparedStatement countStmt = connection
							.prepareStatement(countSql);
					// BoundSql countBS = new BoundSql(
					// mappedStatement.getConfiguration(), countSql,
					// boundSql.getParameterMappings(), parameterObject);
					setParameters(countStmt, mappedStatement, boundSql,
							parameterObject);
					ResultSet rs = countStmt.executeQuery();
					int count = 0;
					if (rs.next()) {
						count = rs.getInt(1);
					}
					rs.close();
					countStmt.close();*/
					int count = 0;
					// 查询参数对象
					Page page = null;
					if (parameterObject instanceof Page) {
						page = (Page) parameterObject;
						//page.setTotalResult(count);
					} else if (parameterObject instanceof Map) {
						Map<String, Object> map = (Map<String, Object>) parameterObject;
						page =map.get("map")!=null?(Page)((HashMap<String,Object>)map.get("map")).get("page"):(Page) map.get("page");
						if (null == page) {
							Iterator it = map.entrySet().iterator();
							while (it.hasNext()) {
								Map.Entry entry = (Map.Entry) it.next();
								if (entry.getValue() instanceof Map) {
									page = (Page)((HashMap<String,Object>)map.get("map")).get("page");
									break;
								}
							}
						}
						if (page == null)
							page = new Page();
						//page.setTotalResult(count);
					} else {
						Field pageField = ReflectHelper.getFieldByFieldName(
								parameterObject, "page");
						if (pageField != null) {
							page = (Page) ReflectHelper
									.getValueByFieldName(parameterObject,
											"page");
							if (page == null)
								page = new Page();
							//page.setTotalResult(count);
							ReflectHelper.setValueByFieldName(parameterObject,
									"page", page);
						} else {
							throw new NoSuchFieldException(parameterObject
									.getClass().getName());
						}
					}
					//System.err.println("page:"+page);
					Dialect dt = null;
					Dialect.Type databaseType = Dialect.Type.valueOf(dialect.toUpperCase());
					switch (databaseType) {
						case MYSQL:
							dt = new MySqlDialect();
							break;
						case ORACLE:
							dt = new OracleDialect();
							break;
						case MSSERVER:
							dt = new MsSqlDialect();
							break;
						default:
							break;
					}
					String sql = boundSql.getSql();
					
					Integer totalResult = page.getTotalResult();
					if(0==totalResult){
						//查询结果记录数量为0时，才执行默认的 获取总记录数量查询sql
						String countSql = dt.getCountString(sql);
						Connection connection = (Connection) ivk.getArgs()[0];
						PreparedStatement countStmt = connection.prepareStatement(countSql);
						setParameters(countStmt, mappedStatement, boundSql,
								parameterObject);
						ResultSet rs = countStmt.executeQuery();
						count = 0;
						if (rs.next()) {
							count = rs.getInt(1);
						}
						rs.close();
						countStmt.close();
						page.setTotalResult(count);
						//page.setSortField(((HashMap<String,Object>)parameterObject).get("sidx").toString());
						//page.setOrder(((HashMap<String,Object>)parameterObject).get("sord").toString());
					}
					
					//System.err.println("boundSql:"+boundSql);
					ReflectHelper.setValueByFieldName(boundSql, "sql", dt
							.getPageSql(sql, page));
				}
			}
		}
		return ivk.proceed();
	}

	@SuppressWarnings("unchecked")
	private void setParameters(PreparedStatement ps,
			MappedStatement mappedStatement, BoundSql boundSql,
			Object parameterObject) throws SQLException {
		ErrorContext.instance().activity("setting parameters").object(
				mappedStatement.getParameterMap().getId());
		List<ParameterMapping> parameterMappings = boundSql
				.getParameterMappings();
		if (parameterMappings != null) {
			Configuration configuration = mappedStatement.getConfiguration();
			TypeHandlerRegistry typeHandlerRegistry = configuration
					.getTypeHandlerRegistry();
			MetaObject metaObject = parameterObject == null ? null
					: configuration.newMetaObject(parameterObject);
			for (int i = 0; i < parameterMappings.size(); i++) {
				ParameterMapping parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					Object value;
					String propertyName = parameterMapping.getProperty();
					PropertyTokenizer prop = new PropertyTokenizer(propertyName);
					if (parameterObject == null) {
						value = null;
					} else if (typeHandlerRegistry
							.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName
							.startsWith(ForEachSqlNode.ITEM_PREFIX)
							&& boundSql.hasAdditionalParameter(prop.getName())) {
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null) {
							value = configuration.newMetaObject(value)
									.getValue(
											propertyName.substring(prop
													.getName().length()));
						}
					} else {
						value = metaObject == null ? null : metaObject
								.getValue(propertyName);
					}
					TypeHandler typeHandler = parameterMapping.getTypeHandler();
					if (typeHandler == null) {
						throw new ExecutorException(
								"There was no TypeHandler found for parameter "
										+ propertyName + " of statement "
										+ mappedStatement.getId());
					}
					typeHandler.setParameter(ps, i + 1, value, parameterMapping
							.getJdbcType());

				}
			}
		}
	}

	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		if (target instanceof StatementHandler) {//这里加了一重判定只有包含的RoutingStatementHandler的当前对象才能被动态代理
			return Plugin.wrap(target, this);
		} else {
			return target;
		}
	}

	public void setProperties(Properties p) {
		dialect = p.getProperty("dialect");
		if (dialect == null || dialect.equals("")) {
			try {
				throw new PropertyException("dialect property is not found!");
			} catch (PropertyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		pageSqlId = p.getProperty("pageSqlId");
		if (dialect == null || dialect.equals("")) {
			try {
				throw new PropertyException("pageSqlId property is not found!");
			} catch (PropertyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
