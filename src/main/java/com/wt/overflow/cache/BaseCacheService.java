package com.wt.overflow.cache;

import com.wt.overflow.bean.Account;

public interface BaseCacheService {
    /**
     * 根据用户id查询当前用户信息
     * @param accountId
     * @return
     */
    Account getAccountById(String accountId);

}
