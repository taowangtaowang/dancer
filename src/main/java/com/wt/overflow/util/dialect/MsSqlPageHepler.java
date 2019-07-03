package com.wt.overflow.util.dialect;

import com.wt.overflow.util.page.Page;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author xdwang
 * 
 * @ceate 2012-12-19 下午8:41:21
 * 
 * @description MySql辅助方法
 * 
 */
public class MsSqlPageHepler {

	/**
	 * 得到分页的SQL
	 * 
	 * @param offset
	 *            偏移量
	 * @param limit
	 *            位置
	 * @return 分页SQL
	 */
	public static String getPageSql(String querySelect,Page page) {

		querySelect = querySelect.toUpperCase();
		querySelect = getLineSql(querySelect);

		int orderIndex = getLastOrderInsertPoint(querySelect);
		int formIndex = getAfterFormInsertPoint(querySelect);
		String select = querySelect.substring(0, formIndex);
		String selectCol = querySelect.substring(7, formIndex);
		String firstCol = getFirstColStr(querySelect);//querySelect.substring(6, getFirstColPoint(querySelect));
		String from = querySelect.substring(formIndex, querySelect.length());
		String orderby = querySelect.substring(orderIndex, querySelect.length());
		if(!StringUtils.hasLength(orderby)){
			orderby = " ORDER BY "+firstCol+" ASC";
			//orderby = " ORDER BY "+page.getSortField()+" "+page.getOrder();
		}
		
		StringBuffer pageSql = new StringBuffer();
		
		
		if (selectCol.indexOf("DISTINCT")!=-1) {
			pageSql.append("SELECT * FROM (")
	    		.append(" SELECT DISTINCT TOP ").append((page.getCurrentResult()+page.getShowCount()))
	    		.append(" row_number() OVER (").append(orderby).append(") n, ")
	    		.append(selectCol.replaceAll("DISTINCT", " ")).append(" ").append(from)
	    		.append(") w where w.n > ").append(page.getCurrentResult()).append(" ORDER BY w.n ASC");
		}else{
			pageSql.append("SELECT * FROM (")
		    	.append(" SELECT TOP ").append((page.getCurrentResult()+page.getShowCount()))
		    	.append(" row_number() OVER (").append(orderby).append(") n, ")
		    	.append(selectCol).append(" ").append(from)
		    	.append(") w where w.n > ").append(page.getCurrentResult()).append(" ORDER BY w.n ASC");
		}
		//pageSql.append(select).append(" W1 ,(")
		//.append("SELECT TOP ").append(page.getCurrentResult()).append(selectCol).append(" ").append(from).append(" ").append(orderby).append(" * )");
		return pageSql.toString();
		
	}
	
	/**
	 * @descrption 得到查询总数的sql
	 * @author xdwang
	 * @create 2012-12-19下午8:41:10
	 * @param querySelect
	 * @return
	 */
	public static String getCountString(String querySelect) {
		querySelect = querySelect.toUpperCase();
		querySelect = getLineSql(querySelect);
		int orderIndex = getLastOrderInsertPoint(querySelect);

		int formIndex = getAfterFormInsertPoint(querySelect);
		String select = querySelect.substring(0, formIndex);

		// 如果SELECT 中包含 DISTINCT 只能在外层包含COUNT
		if (select.indexOf("SELECT DISTINCT") != -1
				//|| querySelect.toUpperCase().indexOf("GROUP BY") != -1
				) {
			return new StringBuffer(querySelect.length()).append(
					//"SELECT COUNT(1) COUNT FROM (").append(
					"SELECT COUNT(1)  FROM (").append(
					querySelect.substring(0, orderIndex)).append(" ) t")
					.toString();
		} else if (querySelect.indexOf("GROUP BY") != -1) {
			//去掉不必要的查询字段
			//String firstCol = getFirstColStr(querySelect);
			String orderbyCol =getLastgGroupByStr(querySelect);
				return new StringBuffer(querySelect.length()).append(
						//"SELECT COUNT(1) COUNT FROM (").append(
						"SELECT COUNT(1)  FROM (SELECT ").append(orderbyCol).append(" ").append(
						querySelect.substring(formIndex, orderIndex)).append(" ) t")
						.toString();
		}else {
			return new StringBuffer(querySelect.length()).append(
					//"SELECT COUNT(1) COUNT ").append(
					"SELECT COUNT(1)  ").append(
					querySelect.substring(formIndex, orderIndex)).toString();
		}
	}

	/**
	 * 得到最后一个Order By的插入点位置
	 * 
	 * @return 返回最后一个Order By插入点的位置
	 */
	private static int getLastOrderInsertPoint(String querySelect) {
		int orderIndex = querySelect.lastIndexOf("ORDER BY");
		if (orderIndex == -1
				|| !isBracketCanPartnership(querySelect.substring(orderIndex,
						querySelect.length()))) {
			//throw new RuntimeException("My SQL 分页必须要有Order by 语句!");
			orderIndex = querySelect.length();
		}
		return orderIndex;
	}
	
	/**
	 * 
	 * @return 返回最后一个group by 的列字符串
	 */
	private static String getLastgGroupByStr(String querySelect) {
		int orderIndex = querySelect.lastIndexOf("GROUP BY");
		if (orderIndex == -1
				|| !isBracketCanPartnership(querySelect.substring(orderIndex,
						querySelect.length()))) {
			//throw new RuntimeException("My SQL 分页必须要有Order by 语句!");
			return "";
		}
		return querySelect.substring(orderIndex+8,querySelect.length());
	}

	/**
	 * 将SQL语句变成一条语句，并且每个单词的间隔都是1个空格
	 * 
	 * @param sql
	 *            SQL语句
	 * @return 如果sql是NULL返回空，否则返回转化后的SQL
	 */
	private static String getLineSql(String sql) {
		return sql.replaceAll("[\r\n]", " ").replaceAll("\\s{2,}", " ");
	}

	/**
	 * 得到SQL第一个正确的FROM的的插入点
	 */
	private static int getAfterFormInsertPoint(String querySelect) {
		String regex = "\\s+FROM\\s+";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(querySelect);
		while (matcher.find()) {
			int fromStartIndex = matcher.start(0);
			String text = querySelect.substring(0, fromStartIndex);
			if (isBracketCanPartnership(text)) {
				return fromStartIndex;
			}
		}
		return 0;
	}

	/**
	 * 判断括号"()"是否匹配,并不会判断排列顺序是否正确
	 * 
	 * @param text
	 *            要判断的文本
	 * @return 如果匹配返回TRUE,否则返回FALSE
	 */
	private static boolean isBracketCanPartnership(String text) {
		if (text == null
				|| (getIndexOfCount(text, '(') != getIndexOfCount(text, ')'))) {
			return false;
		}
		return true;
	}
	
	/**
	 * 得到SQL第一个正确的FROM的的插入点
	 */
	private static int getFirstColPoint(String querySelect) {
		String regex = ",";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(querySelect);
		while (matcher.find()) {
			int dIndex = matcher.start(0);
			String text = querySelect.substring(0, dIndex);
			if (isBracketCanPartnership(text)) {
				return dIndex;
			}
		}
		return 0;
	}
	
	/**
	 * 获取第一个查询列名称
	 * @param querySelect
	 * @return
	 */
	private static String getFirstColStr(String querySelect) {
		String regex = ",";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(querySelect);
		while (matcher.find()) {
			int dIndex = matcher.start(0);
			pattern = Pattern.compile("\\s+AS\\s+", Pattern.CASE_INSENSITIVE);
			Matcher matcher1 = pattern.matcher(querySelect);
			while (matcher1.find()) {
				int asIndex = matcher1.start(0);
				if(asIndex<dIndex){
					return querySelect.substring(6,asIndex);
				}
			}
			pattern = Pattern.compile("\\s+DISTINCT\\s+", Pattern.CASE_INSENSITIVE);
			Matcher matcher2 = pattern.matcher(querySelect);
			while (matcher2.find()) {
				int disIndex = matcher2.start(0);
				if(disIndex<dIndex){
					return querySelect.substring(disIndex,dIndex);
				}
			}
			return querySelect.substring(6,dIndex);
		}
		return "";
	}

	/**
	 * 得到一个字符在另一个字符串中出现的次数
	 * 
	 * @param text
	 *            文本
	 * @param ch
	 *            字符
	 */
	private static int getIndexOfCount(String text, char ch) {
		int count = 0;
		for (int i = 0; i < text.length(); i++) {
			count = (text.charAt(i) == ch) ? count + 1 : count;
		}
		return count;
	}
}
