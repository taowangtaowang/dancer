package com.wt.overflow.service.impl;

import com.wt.overflow.bean.Account;
import com.wt.overflow.dao.AccountMapper;
import com.wt.overflow.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


/**
 * 登录初始化相关
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private AccountMapper accountMapper;

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public List<Account> queryByAccount(String account) {
		logger.info("日志测试service包");
		Example accountExample = new Example(Account.class);
		Example.Criteria accountCriteria = accountExample.createCriteria();
		accountCriteria.andEqualTo("account",account);
		return accountMapper.selectByExample(accountExample);
	}

}
