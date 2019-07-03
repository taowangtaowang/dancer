package com.wt.overflow.dao;

import com.wt.overflow.bean.SysRole;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.Map;

@MapperScan
public interface SysRoleMapper {

	List<SysRole> queryAllDataByCategory(Map<String, Object> parameter);

	List<Map<String, Object>> queryRoleByPage(Map<String, Object> parameter);

	void addRole(Map<String, Object> parmap);

	void updateRoleByRoleId(Map<String, Object> parmap);

	Integer deleteRole(Map<String, Object> parmap);

	SysRole queryAllDuty(Map<String, Object> parameter);

	SysRole queryOneByRoleId(String roleId);
	
}