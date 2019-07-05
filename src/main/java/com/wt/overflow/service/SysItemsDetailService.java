package com.wt.overflow.service;

import com.wt.overflow.bean.SysItemsdetail;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


public interface SysItemsDetailService {

	List<SysItemsdetail> queryAllData();

	List<Map<String, Object>> queryMainRole();

	SysItemsdetail queryOneByFId(String keyword);

	Integer deleteItemsDetailByFId(String keyValue, HttpServletRequest request);

	int updateItemsDetail(HttpServletRequest request, String keyValue, String f_ItemName,
                              String f_ItemCode, int f_SortCode, int f_IsDefault, int f_EnabledMark, String f_Description,
                              String f_ItemId);

	List<Map<String, Object>> getSelectJson(String enCode);
	

}
