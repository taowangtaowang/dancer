package com.wt.overflow.service.impl;

import com.wt.overflow.bean.Ab;
import com.wt.overflow.bean.Account;
import com.wt.overflow.bean.Cd;
import com.wt.overflow.dao.AbMapper;
import com.wt.overflow.dao.AccountMapper;
import com.wt.overflow.dao2.CdMapper;
import com.wt.overflow.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AbMapper abMapper;
    @Autowired
    private CdMapper cdMapper;


    @Transactional
    @Override
    public int testTransactionManager(Account account) {
        account.setId(999);
        account.setAccount("testTransactionManager");
        int res = accountMapper.insert(account );
        Cd cd = new Cd();cd.setId(10);
        cdMapper.insert(cd);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i = 1/0;
        return 1;
    }


    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public void testSomeDataSourceTransaction() {
        List<Ab> abList = abMapper.selectAll();
        List<Cd> cdList = cdMapper.selectAll();
        System.out.println("abList"+abList+"cdList"+cdList);
    }

}
