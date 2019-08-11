package com.wt.overflow.service;

import com.wt.overflow.bean.Account;

import java.util.List;


public interface LoginService {

	List<Account> queryByAccount(String account);

}
