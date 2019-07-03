package com.wt.overflow.dao;

import com.wt.overflow.bean.SysModule;
import com.wt.overflow.bean.SysModules;
import com.wt.overflow.bean.SysOnes;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.Map;

@MapperScan
public interface SysModuleMapper {

	List<SysModule> queryAllSysModuleByIsAdmin(Map<String, Object> parameter);

	List<SysModule> queryAllData(Map<String, Object> parameter);
	
	List<SysModules> findListPage(Map<String, Object> parameter);
	
	SysModule selectByPrimaryKey(String fId);
	
	boolean insertModule(SysModule sysModule);
	
	boolean deleteByModuleKey(SysModule sysModule);
    
	boolean updateByPrimaryKeySelective(SysModule sysModule);
	
	List<SysModules> findListParentid(@Param(value = "fParentid") String Parentid);
	
	SysModule SelectByfParentid(@Param(value = "fParentid") String fParentid);
	
	List<SysModules> findListFullName(@Param(value = "fFullname") String fFullname);
	
	List<SysOnes> findList();
	
	
}