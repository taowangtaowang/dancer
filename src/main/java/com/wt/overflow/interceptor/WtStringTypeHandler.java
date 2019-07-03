package com.wt.overflow.interceptor;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.VARCHAR)
public class WtStringTypeHandler extends BaseTypeHandler<String> {
	// 可以覆盖类型处理程序或创建自己的处理 不支持的或非标准的类型。 为此,实现的接口
	// org.apache.ibatis.type.TypeHandler
	// 或扩展方便类 org.apache.ibatis.type.BaseTypeHandler
	// 和选择将其映射到一个JDBC类型。这里选择String作为例子

	public WtStringTypeHandler() {
	}

	public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setString(i, parameter + "+typeHandlers");
	}

	public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return rs.getString(columnName);
	}

	public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return rs.getString(columnIndex);
	}

	public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return cs.getString(columnIndex);
	}

}
