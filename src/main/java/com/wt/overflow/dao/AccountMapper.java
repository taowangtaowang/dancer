package com.wt.overflow.dao;

import com.wt.overflow.bean.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {

    List<Account> selectByExample(Account accounts);

    int insert(Account account);
}