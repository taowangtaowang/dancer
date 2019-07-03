package com.wt.overflow.util.dialect;


import com.wt.overflow.util.page.Page;

public interface Dialect {
	
	@SuppressWarnings("unused")
	public static enum Type {
		MYSQL {
			public String getValue() {
				return "mysql";
			}
		},
		MSSERVER {
			public String getValue() {
				return "msserver";
			}
		},
		ORACLE {
			public String getValue() {
				return "oracle";
			}
		}
	}

	public abstract String getCountString(String sql);
	
	public abstract String getPageSql(String sql, Page page);
}
