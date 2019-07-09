package com.wt.overflow.dao;

import com.wt.overflow.bean.SysModule;
import com.wt.overflow.bean.SysModules;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@MapperScan
public interface SysModuleMapper extends Mapper<SysModule> {

	List<SysModule> queryAllSysModuleByIsAdmin(Map<String, Object> parameter);

	List<SysModule> queryAllData(Map<String, Object> parameter);
	
	List<SysModules> findListPage(Map<String, Object> parameter);

	List<SysModules> findListParentid(@Param(value = "parentId") String parentId);
	
	SysModule SelectByfParentid(@Param(value = "parentid") String parentid);
	
	List<SysModules> findListFullName(@Param(value = "fullname") String fullname);

	
}