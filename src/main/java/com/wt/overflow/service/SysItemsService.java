package com.wt.overflow.service;

import com.wt.overflow.bean.SysItems;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


public interface SysItemsService {

	List<Map<String, Object>> queryAllDataByZtree();

	List<Map<String, Object>> queryAllDataByPage(Integer rows, Integer page, String sidx, String sord, String keyword, String itemId);

	Integer deleteItemsByFId(String keyword, HttpServletRequest request);

	SysItems queryOneByFId(String keyword);

	int updateItems(HttpServletRequest request, String keyValue, String f_FullName, String f_EnCode,
                        int f_SortCode, int f_EnabledMark, String f_ParentId, String fDescription);

	Map<String, Object> queryAllItemsList();

}
