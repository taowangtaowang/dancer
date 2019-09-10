package com.wt.overflow.service.impl;

import com.wt.overflow.bean.LoginLog;
import com.wt.overflow.dao.LoginLogMapper;
import com.wt.overflow.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OperationLogServiceImpl implements LoginLogService {

    @Autowired
    LoginLogMapper loginLogMapper;


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void add(LoginLog loginLog) {
        List<LoginLog> logList = new ArrayList<>();logList.add(loginLog);
        loginLogMapper.adds(logList);
        System.out.println("这里是apo切面日志："+loginLog.toString());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void adds(List<LoginLog> loginLogList) {
        loginLogMapper.adds(loginLogList);
    }
}
