package com.wt.overflow.util;

import java.util.UUID;

/**
 * UUID 工具类
 * @author hzw
 */
public class UUIDUtil {

	/**
	 * 创建UUId
	 * @return
	 */
	public static String getUUID(){
	 	UUID Uuid= UUID.randomUUID();
		return Uuid.toString().replace("-", "") ;
	}
	
}
