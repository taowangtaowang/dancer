package com.wt.overflow.service;

import javax.servlet.http.HttpServletRequest;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public interface SysRoleAuthorizeMenuService {

	List<Map<String, Object>> queryAllData(String roleId) throws IllegalAccessException, InstantiationException, InvocationTargetException, IntrospectionException;

	Map<String, Object> updateSysRoleAuthorizeMenu(
			HttpServletRequest request, String keyValue, String f_OrganizeId, String f_FullName,
			String f_EnCode, String f_Type, Integer f_SortCode, int f_AllowEdit, int f_AllowDelete,
			int f_EnabledMark, String f_Description, String permissionIds);
	

}
