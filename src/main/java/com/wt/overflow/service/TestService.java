package com.wt.overflow.service;

import com.wt.overflow.bean.SysUser;

public interface TestService {
    int testTransactionManager(SysUser sysUser);

    void testSomeDataSourceTransaction();
}
