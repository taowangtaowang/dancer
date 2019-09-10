package com.wt.overflow.dao2;

import com.wt.overflow.bean.Cd;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CdMapper {

    List<Cd> selectAll();

    int insert(Cd cd);
}