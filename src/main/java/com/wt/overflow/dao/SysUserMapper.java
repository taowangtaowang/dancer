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

	/**
	 * @Description 查询未绑定窗口的用户
	 * @author wumin
	 * @date 2018/5/2 14:31
	 * @param businessTypeId 业务类型ID
	 * @return java.util.List<com.dome.bean.sys.SysUser>
	 */
	List<SysUser> selectWindowsUserList(@Param("businessTypeId") String businessTypeId);

	List<SysUser> querySysUserByOrganId(SysUser sysUser);
}