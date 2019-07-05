package com.wt.overflow.dao;


import com.wt.overflow.bean.SysModulebutton;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.Map;

@MapperScan
public interface SysModuleButtonMapper {

	List<SysModulebutton> queryAllAuthorizeButtonform(Map<String, Object> parameter);

	List<SysModulebutton> queryAllData(Map<String, Object> parameter);
}