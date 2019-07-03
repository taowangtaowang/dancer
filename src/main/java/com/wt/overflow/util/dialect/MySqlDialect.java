package com.wt.overflow.util.dialect;


import com.wt.overflow.util.page.Page;

public class MySqlDialect implements Dialect {

	public String getPageSql(String sql, int offset, int limit) {
		return MySqlPageHepler.getPageSql(sql, offset, limit);
	}

	public String getPageSql(String sql, Page page) {
		// TODO Auto-generated method stub
		return MySqlPageHepler.getPageSql(sql, page.getCurrentResult(), page.getShowCount());
	}

	
	public String getCountString(String sql) {
		// TODO Auto-generated method stub
		return MySqlPageHepler.getCountString(sql);
	}

}
