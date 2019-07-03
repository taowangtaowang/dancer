package com.wt.overflow.dao;


import com.wt.overflow.bean.SysItems;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.Map;

@MapperScan
public interface SysItemsMapper {

	List<SysItems> queryAllData();

	List<SysItems> queryAllByPId(Map<String, Object> parmap);

	Integer deleteItemsByFId(Map<String, Object> parameter);

	SysItems queryOneById(String keyword);

	Integer addSysItems(SysItems sysItems);

	Integer updateItems(SysItems sysItems);

	List<SysItems> queryByEnCode(SysItems sysItems);
	
	List<SysItems> queryCodeName(); 
	
}