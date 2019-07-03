package com.wt.overflow.dao;

import com.wt.overflow.bean.SysItemsdetail;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.Map;

@MapperScan
public interface SysItemsDetailMapper {

	List<SysItemsdetail> queryAllData();

	List<Map<String, Object>> queryMainRole();

	List<Map<String, Object>>  queryAllDataByPage(Map<String, Object> parameter);

	SysItemsdetail queryOneByFId(String keyword);

	Integer deleteItemsDetailByFId(Map<String, Object> parameter);

	Integer addSysItemsDetail(SysItemsdetail sysItemsdetail);

	Integer updateItemsDetail(SysItemsdetail sysItemsdetail);

	List<Map<String, Object>> getSelectJson(@Param("enCode") String enCode);
}