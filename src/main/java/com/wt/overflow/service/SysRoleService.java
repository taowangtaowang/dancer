package com.wt.overflow.service;

import com.wt.overflow.bean.SysRole;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


public interface SysRoleService {

	Map<String, Object> queryRoleByPage(Integer rows, Integer page, String sidx, String sord, String keyword);

	Integer deleteRole(HttpServletRequest request, String roleId);

	List<Map<String, Object>> queryAllDuty(Integer rows, Integer page, String sidx, String sord, String keyword);

	SysRole queryOneByRoleId(String roleId);

	Map<String, Object> updateDuty(HttpServletRequest request, SysRole sysRole, String keyword);

}
