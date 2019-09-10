package com.wt.overflow.service;

import com.wt.overflow.bean.Account;

public interface TestService {
    int testTransactionManager(Account account);

    void testSomeDataSourceTransaction();
}
