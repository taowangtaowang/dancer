package com.wt.overflow.dao;

import com.wt.overflow.bean.Account;
import org.mybatis.spring.annotation.MapperScan;
import tk.mybatis.mapper.common.Mapper;

@MapperScan
public interface AccountMapper extends Mapper<Account> {

}