package com.wt.overflow.service.impl;

import com.wt.overflow.bean.Ab;
import com.wt.overflow.bean.Cd;
import com.wt.overflow.bean.SysUser;
import com.wt.overflow.dao.AbMapper;
import com.wt.overflow.dao.SysUserMapper;
import com.wt.overflow.dao2.CdMapper;
import com.wt.overflow.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private AbMapper abMapper;
    @Autowired
    private CdMapper cdMapper;


    @Transactional
    @Override
    public int testTransactionManager(SysUser sysUser) {
        //sysUser.setId("testTransactionManager");
        //int res = sysUserMapper.insertSelective(sysUser );
        //int i = 1/0;
        return 1;
    }


    @Override
    public void testSomeDataSourceTransaction() {
        Example abExample = new Example(Ab.class);
        List<Ab> abList = abMapper.selectByExample(abExample);
        Example cdExample = new Example(Cd.class);
        List<Cd> cdList = cdMapper.selectByExample(cdExample);
        System.out.println("abList"+abList+"cdList"+cdList);
    }

}
