package com.wt.overflow.dao;

import com.wt.overflow.bean.SysUser;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@MapperScan
public interface SysUserMapper extends Mapper<SysUser> {

	List<SysUser> queryByLoginName(Map<String, Object> parameter);

	List<Map<String, Object>> queryUserByPage(Map<String, Object> parameter);

	void addSysUser(SysUser sysUser);

	void updateSysUser(SysUser sysUser);

	Integer deleteSysUser(Map<String, Object> parameter);

	SysUser queryOneBySysUserId(String keyValue);

	Integer updateSysUserEnabled(Map<String, Object> parameter);

	void updateSysUserReset(Map<String, Object> parameter);

	List<SysUser> selectAllExitSysUsers();

	Integer updateSysUserHeadIcon(Map<String, Object> parameter);

	List<SysUser> querySysUserByOrganId(SysUser sysUser);
}