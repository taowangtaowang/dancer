package com.wt.overflow.service.impl;

import com.wt.overflow.dao.SysOrganizeMapper;
import com.wt.overflow.dao.SysUserLogOnMapper;
import com.wt.overflow.dao.SysUserMapper;
import com.wt.overflow.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserLogOnMapper sysUserLogOnMapper;
    @Autowired
    private SysOrganizeMapper sysOrganizeMapper;


    @Override
    public int testTransactionManager() {




        return 0;
    }
}
