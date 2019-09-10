package com.wt.overflow.dao;

import com.wt.overflow.bean.LoginLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoginLogMapper {

    void adds(List<LoginLog> list);
}
