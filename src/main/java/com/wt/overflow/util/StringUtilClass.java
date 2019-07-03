package com.wt.overflow.util;

import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * 	字符串工具类
 * @Copyright 
 * @author hzw
 * @Date:2015年10月8日
 */
public class StringUtilClass {

	/**
	 * 判断字符串是否为空
	 * @author hzw
	 * @Date 2015年10月9日
	 * @param str
	 * @return 是否为空 是空返回true 不是空为false
	 */
	public static boolean isNotEmpty(String str) {
		boolean flag = false;
		if (str != null && !str.equals("")) {
			flag = true;
		}
		return flag;
	}	
	
	
	/**
	 * 处理中文字符串的函数 使用utf-8 防止乱码
	 * 
	 * @author hzw
	 * @Date 2015年10月9日
	 * @param str
	 *            传入字符串类型
	 * @return
	 */
	public static String codeString(String str) {
		String s = str;
		try {
			byte[] temp = s.getBytes("utf-8");
			s = new String(temp);
			return s;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return s;
		}
	}
    
	
    /**
     * 	检查字符串是否都是大写. 
     * @param str
     *            源字符串 
     * @return String
     */
    public static boolean isAllUpperCase(String str) {
        if (str == null || !StringUtilClass.isNotEmpty(str)) {  
            return false;  
        }
        int sz = str.length();  
        for (int i = 0; i < sz; i++) {
            if (Character.isUpperCase(str.charAt(i)) == false) {  
                return false;
            }  
        }  
        return true;  
    }
    /** 
     * 将< 转换为 &lt; 将 > 转换为 &gt;
     */
    public static String filterStr(String str){
		if(StringUtils.hasLength(str)){
			//str = str.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;").replaceAll("'", "&#39;");;//.replaceAll("&", "&amp;");//
			str = str.replaceAll("<", "&lt;").replaceAll(">", "&gt;");//.replaceAll("\"", "&quot;").replaceAll("'", "&#39;");;//.replaceAll("&", "&amp;");//
		}
		return str;
	}
    
}