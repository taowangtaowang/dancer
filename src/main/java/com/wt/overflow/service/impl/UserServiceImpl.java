package com.wt.overflow.service.impl;

import com.wt.overflow.bean.Account;
import com.wt.overflow.dao.AccountMapper;
import com.wt.overflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AccountMapper accountMapper;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<Account> getManageUser() {
        List<Account> accountList = accountMapper.selectByExample(new Account());
        return accountList;
    }
}
