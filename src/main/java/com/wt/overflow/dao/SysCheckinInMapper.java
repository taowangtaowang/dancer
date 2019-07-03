package com.wt.overflow.dao;


import com.wt.overflow.bean.SysCheckingIn;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.Map;

@MapperScan
public interface SysCheckinInMapper {

	List<SysCheckingIn> queryAllData(Map<String, Object> parmap);
	

}
