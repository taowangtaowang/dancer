package com.wt.overflow.service.impl;

import com.wt.overflow.bean.SysUser;
import com.wt.overflow.dao.SysUserMapper;
import com.wt.overflow.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private SysUserMapper sysUserMapper;


    @Transactional
    @Override
    public int testTransactionManager(SysUser sysUser) {
        sysUser.setId("testTransactionManager");
        int res = sysUserMapper.insertSelective(sysUser );
        //int i = 1/0;
        return res;
    }

}
