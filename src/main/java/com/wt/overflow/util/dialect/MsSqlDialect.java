package com.wt.overflow.util.dialect;


import com.wt.overflow.util.page.Page;

public class MsSqlDialect implements Dialect {

	
	public String getPageSql(String sql, Page page) {
		// TODO Auto-generated method stub
		return MsSqlPageHepler.getPageSql(sql, page);
	}			
	
/*	public String getPaginationSql(String sql, int pageNo, int pageSize) {
	       return "select top " + pageSize + " from (" + sql
	              + ") t where t.id not in (select top " + (pageNo-1)*pageSzie + " t1.id from ("
	              + sql + ") t1)";
	    }*/

	
	public String getCountString(String sql) {
		return MsSqlPageHepler.getCountString(sql);
	}

}
