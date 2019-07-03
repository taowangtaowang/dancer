package com.wt.overflow.dao;


import com.wt.overflow.bean.SysRoleauthorize;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.Map;

@MapperScan
public interface SysRoleauthorizeMapper {

	List<Map<String, Object>> queryMenuByRoleId(Map<String, Object> parameter);

	Integer addSysRoleauthorize(SysRoleauthorize sysRoleauthorize);

	void deleteAllByRoleId(String getfId);

}