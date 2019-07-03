package com.wt.overflow.dao;


import com.wt.overflow.bean.SysModuleButton;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.Map;

@MapperScan
public interface SysModuleButtonMapper {

	List<SysModuleButton> queryAllAuthorizeButtonform(Map<String, Object> parameter);

	List<SysModuleButton> queryAllData(Map<String, Object> parameter);
}