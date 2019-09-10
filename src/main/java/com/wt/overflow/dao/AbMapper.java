package com.wt.overflow.dao;

import com.wt.overflow.bean.Ab;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AbMapper  {

    List<Ab> selectAll();
}