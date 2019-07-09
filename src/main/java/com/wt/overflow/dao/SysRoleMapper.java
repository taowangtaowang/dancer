package com.wt.overflow.dao;

import com.wt.overflow.bean.SysRole;
import org.mybatis.spring.annotation.MapperScan;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@MapperScan
public interface SysRoleMapper extends Mapper<SysRole>{

	List<SysRole> queryAllDataByCategory(Map<String, Object> parameter);

	List<Map<String, Object>> queryRoleByPage(Map<String, Object> parameter);

	void addRole(Map<String, Object> parmap);

	void updateRoleByRoleId(Map<String, Object> parmap);

	Integer deleteRole(Map<String, Object> parmap);

	SysRole queryOneByRoleId(String roleId);
	
}