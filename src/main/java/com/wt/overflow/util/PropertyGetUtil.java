package com.wt.overflow.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;


public class PropertyGetUtil {
	public static void main(String[] args) {
		try {
			System.err.println(new PropertyGetUtil().getProperties("filter.url","D:\\wtsoft\\eclipse\\workspaceStudy\\myweb\\src\\config\\filter.properties").size());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 随意获取配置文件定义的 相关值s
	 * @param parm 配置文件定义的key 
	 * @param propertiename   配置文件名称
	 * @return
	 * @throws IOException
	 * @name wangt	
	 * @Date 2017年9月7日 上午10:29:48
	 */
	public  List<String> getProperties(String parm,String propertiename) throws IOException {
		Properties pps = new Properties();
		//InputStream is = this.getClass().getResourceAsStream("/application.properties");
		InputStream is = this.getClass().getResourceAsStream(propertiename);
		pps.load(is);
		List<String> strlist = new ArrayList<String>();
		String returnStr ="";
		@SuppressWarnings("rawtypes")
		Enumeration enum1 = pps.propertyNames();// 得到配置文件的名字
		while (enum1.hasMoreElements()) {
			String strKey = (String) enum1.nextElement();
			if (strKey.equals(parm)) {
				returnStr = pps.getProperty(strKey);
				returnStr.split(",");
				Collections.addAll(strlist, returnStr.split(","));
			}
		}
		if(is!=null){
			is.close();
		}
		return strlist ;
	}
}
