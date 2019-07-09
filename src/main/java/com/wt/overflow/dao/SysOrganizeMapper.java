package com.wt.overflow.dao;


import com.wt.overflow.bean.SysOrganize;
import org.mybatis.spring.annotation.MapperScan;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@MapperScan
public interface SysOrganizeMapper extends Mapper<SysOrganize>{

	public List<SysOrganize> queryAllData(Map<String, Object> parameter);


}