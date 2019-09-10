package com.wt.overflow.dao;

import com.wt.overflow.bean.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    List<Menu> selectAll();
}