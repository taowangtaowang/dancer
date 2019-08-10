package com.wt.overflow.service.impl;

import com.wt.overflow.bean.OperationLog;
import com.wt.overflow.dao.OperationLogMapper;
import com.wt.overflow.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationLogServiceImpl implements OperationLogService {

    @Autowired
    OperationLogMapper operationLogMapper;

    public void add(OperationLog opLog) {
        //operationLogMapper.insertSelective(opLog);
        System.out.println("这里是apo切面日志："+opLog.toString());
    }
}
