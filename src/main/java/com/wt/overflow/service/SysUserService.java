package com.wt.overflow.service;

import com.wt.overflow.bean.SysUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


public interface SysUserService {

	Map<String, Object> queryUserByPage(Integer rows, Integer page, String sidx, String sord, String keyword);

	Integer updateSysUser(HttpServletRequest request, SysUser sysUser, String f_UserPassword, String keyValue);

	Integer deleteSysUser(HttpServletRequest request, String keyValue);

	SysUser queryOneBySysUserId(String keyValue);

	Integer updateSysUserEnabled(HttpServletRequest request, String keyValue);

	Integer updateSysUserReset(HttpServletRequest request, SysUser sysUser, String keyValue);

	Integer updateSysUserHeadIcon(HttpServletRequest request, String headIcon, String fId);

	Map<String, Object> querySysUserByOrganId(HttpServletRequest request);

}
