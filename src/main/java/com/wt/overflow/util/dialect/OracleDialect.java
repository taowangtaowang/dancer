package com.wt.overflow.util.dialect;


import com.wt.overflow.util.page.Page;

public class OracleDialect implements Dialect {

	public String getPageSql(String sql, Page page) {
		// TODO Auto-generated method stub
		if(null==page){
			return sql;
		}
		sql = sql.trim();
		boolean isForUpdate = false;
		if (sql.toLowerCase().endsWith(" for update")) {
			sql = sql.substring(0, sql.length() - 11);
			isForUpdate = true;
		}
		StringBuffer pageSql = new StringBuffer();
		pageSql.append("select * from (select tmp_tb.*,ROWNUM row_id from (");
		pageSql.append(sql);
		pageSql.append(")  tmp_tb where ROWNUM<=");
		pageSql.append(page.getCurrentResult() + page.getShowCount());
		pageSql.append(") where row_id>");
		pageSql.append(page.getCurrentResult());
		if (isForUpdate) {
			pageSql.append(" for update");
		}
		return pageSql.toString();
	}

	
	public String getCountString(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

}
