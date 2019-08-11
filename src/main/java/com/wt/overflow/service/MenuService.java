package com.wt.overflow.service;

import com.wt.overflow.bean.Account;

import java.util.List;
import java.util.Map;

public interface MenuService {

    List<Map<String, Object>> selectShowMenus(Account account);
}
