package com.wt.overflow.dao;

import com.wt.overflow.bean.OperationLog;
import org.mybatis.spring.annotation.MapperScan;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@MapperScan
public interface OperationLogMapper extends Mapper<OperationLog>{

    void adds(List<OperationLog> list);
}
