package com.wt.overflow.service;

import com.wt.overflow.bean.SysModule;
import com.wt.overflow.bean.SysModules;
import com.wt.overflow.bean.SysOnes;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface SysModuleService {

	List<SysModule> queryAllSysModuleByIsAdmin(boolean isAdmin, String userRoleId);
    
	List<SysModules> findListPage(Map<String, Object> parameter);
	
	SysModule selectByPrimaryKey(String fId);
	
	boolean insertSysModule(SysModule sysModule);
	
	boolean deleteByModuleKey(SysModule sysModule);
    
	boolean updateByPrimaryKeySelective(SysModule sysModule);
	
	List<SysModules> findListParentid(@Param(value = "Parentid") String Parentid);
	
	SysModule SelectByfParentid(String fParentid);
	
	List<SysOnes> findList();
	
	List<SysModules> findListS(String fFullname);
}
