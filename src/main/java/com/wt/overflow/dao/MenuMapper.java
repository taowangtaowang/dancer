package com.wt.overflow.dao;

import com.wt.overflow.bean.Menu;
import org.mybatis.spring.annotation.MapperScan;
import tk.mybatis.mapper.common.Mapper;

@MapperScan
public interface MenuMapper extends Mapper<Menu> {

}